package com.hanb.service;

import java.security.PrivateKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanb.dao.MemberDao;
import com.hanb.enums.Member;
import com.hanb.security.BCrypt;
import com.hanb.security.Rsa;
import com.hanb.vo.LoginMemberVo;
import com.hanb.vo.MemberVo;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	@Autowired
	private Rsa rsa;
	@Autowired
	private MailService sender;

	public boolean checkMember(String m_id, String m_pwd, PrivateKey privateKey) {
		final boolean LOGIN_FAILED = false;

		String pwd = dao.getPwd(m_id);
		if (pwd == null) {
			return LOGIN_FAILED;
		} else {
			return BCrypt.checkpw(rsa.decryptRsa(m_pwd, privateKey), pwd);
		}
	}

	public LoginMemberVo login(String m_id) {
		return dao.login(m_id);
	}

	public boolean insertMember(MemberVo m, PrivateKey privateKey) {
		m.setM_pwd(BCrypt.hashpw(rsa.decryptRsa(m.getM_pwd(), privateKey), BCrypt.gensalt(12)));
		int re = dao.insertMember(m);
		if (re == 1) {
			String title = "PUP�� �ı��� �ǽŰ��� ȯ���մϴ�!!!!!!!!!!!!";
			String text = "<h2>ȯ���մϴ�! " + m.getM_name() + "��</h2><br /> " + m.getM_name()
					+ "���� ȸ�������� ȯ���ϸ�, ȸ������ ȸ������������ ������ �ϱ� ���Ͽ� �Ʒ��� ��ũ�� Ŭ���Ͽ� �ֽñ� �ٶ��ϴ�.<br /><br /><br /> "
					+ "<a href='http://203.236.209.145:8087/controller/upgradeMember.do?m_id=" + m.getM_id()
					+ "'>ȸ������</a> <br /><br /> �׻� �� ���� ���񽺷� ������ �ϱ� ���� ����ϰڽ��ϴ�. �����մϴ�.";
			sender.send(m.getM_email(), title, text);
			return true;
		}
		return false;
	}

	public boolean generateTempPwdAndSendEmail(String m_id, String m_email) {
		MemberVo m = new MemberVo();
		m.setM_id(m_id);
		m.setM_email(m_email);
		int re = dao.checkIdPwd(m);
		if (re == 1) {

			String tmpPwd = String.valueOf(System.currentTimeMillis());
			String securedPwd = BCrypt.hashpw(tmpPwd, BCrypt.gensalt(12));
			m.setM_pwd(securedPwd);

			boolean updateResult = updateMember(m);
			if (updateResult == true) {
				String title = "PUP�Դϴ�. ȸ������ �ӽ� ��й�ȣ�߱��� ��û�ϼ̽��ϴ�.";
				String content = "�ȳ��Ͻʴϱ�? ȸ������ ��й�ȣ ã�⼭�񽺸� �̿��ϼż� �ӽ� ��й�ȣ�� �߱��� �帳�ϴ�." + "<br /><br /> <h2>�ӽú�й�ȣ : "
						+ tmpPwd + " </h2><br /><br />" + "�׻� �� ���� ���񽺸� ���� ����ϴ� PUP�� �ǵ��� �ϰڽ��ϴ�. �����մϴ�.";
				sender.send(m.getM_email(), title, content);
				return true;
			}
		}
		return false;
	}

	public boolean updateMember(MemberVo m) {
		int re = dao.updateMember(m);
		if (re == 1)
			return true;
		else
			return false;
	}

	public int upgradeMember(String m_id) {
		// TODO Auto-generated method stub
		int upgradeCount = -1;
		int g_num = dao.detailMember(m_id).getG_num();
		if (g_num == 10) {
			MemberVo m = new MemberVo();
			m.setM_id(m_id);
			m.setG_num(Member.MEMBER.getNumber());
			upgradeCount = dao.updateMember(m);
		}
		return upgradeCount;
	}

	public String getMemberId(String m_email) {
		// TODO Auto-generated method stub
		return dao.getMemberId(m_email);
	}

}
