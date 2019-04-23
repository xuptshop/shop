package cn.shop.adminuser.action;

import cn.shop.merchant.service.MerchantService;
import cn.shop.user.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import cn.shop.adminuser.service.AdminUserService;
import cn.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {
	// 模型驱动使用的对象
	private AdminUser adminUser = new AdminUser();

	public AdminUser getModel() {
		return adminUser;
	}

	// 注入AdminUserService
	private AdminUserService adminUserService;
	// 注入MerchantService
	private MerchantService merchantService;

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	// 后台登录的执行的方法
	public String login() {
		try{
			AdminUser existAdminUser = null;
            User merchantUser = null;

            //判断是超级管理员还是商家用户
			String userType = ServletActionContext.getRequest().getParameter("user-type");
			if(StringUtils.isEmpty(userType)) {
				ServletActionContext.getResponse().getWriter().write("页面访问失败，请刷新重试!");
				this.addActionError("页面访问失败，请刷新重试!");
				return "error";
			}else if(userType.equals("merchantUser")){ //商家用户
			    User user = new User(adminUser.getUsername(), adminUser.getPassword());
                merchantUser =  merchantService.login(user);
			}else if(userType.equals("adminUser")) {  //超级管理员
				// 调用service方法完成登录
				existAdminUser = adminUserService.login(adminUser);
			}else{
				this.addActionError("页面访问失败，请刷新重试!");
				return "error";
			}


			if (existAdminUser == null && merchantUser == null) {
				// 账号或密码错误
				this.addActionError("账号或密码错误!");
				return "loginFail";
			}

			// 登录成功:
            if(existAdminUser != null) {
			    ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
            } else {
			    ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", merchantUser);
            }
			ServletActionContext.getRequest().getSession().setAttribute("userType", userType);
			return "loginSuccess";

		}catch (Exception e) {
			e.printStackTrace();
			this.addActionError("页面访问失败，请联系系统管理人员!");
			return "error";
		}

	}

	// 后台登录首页
	public String index() {
		return "loginIndex";
	}
}
