package cn.shop.product.adminaction;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import cn.shop.user.vo.User;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import cn.shop.categorysecond.service.CategorySecondService;
import cn.shop.categorysecond.vo.CategorySecond;
import cn.shop.product.service.ProductService;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理的Action
 * 
 * @author striner
 * 
 */
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	// 模型驱动使用的对象
	private Product product = new Product();

	public Product getModel() {
		return product;
	}

	// 接收page参数
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 注入ProductService
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 注入CategorySecondService
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 文件上传需要的三个属性:
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	// 查询所有的商品:
	public String findAll() {
		//从session中取userType判断是商家用户还是超级管理员
		String userType = (String) ServletActionContext.getRequest().getSession().getAttribute("userType");
		if ("adminUser".equals(userType)) {  //超级管理员
			PageBean<Product> pageBean = productService.findByPage(page);
			// 将PageBean数据存入到值栈中.
			ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		}else{  //商家用户
			int uid = ((User) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser")).getUid();
			PageBean<Product> pageBean = productService.findByMerchantPage(page, uid);
			// 将PageBean数据存入到值栈中.
			ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		}

		// 页面跳转
		return "findAll";
	}

	// 跳转到添加页面的方法:
	public String addPage() {
		// 查询所有的二级分类:
		List<CategorySecond> csList = categorySecondService.findAll();
		// 将二级分类的数据显示到页面上
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "addPageSuccess";
	}

	// 保存商品的方法:
	public String save() throws Exception {
		// 将提交的数据添加到数据库中.
		SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date2=temp.format(new Date());
		product.setPdate(temp.parse(date2));

		String csid = ServletActionContext.getRequest().getParameter("categorySecond.csid");
		product.setCsid(csid);
//		product.setPdate(new Date());
		// product.setImage(image);
		if(upload != null){
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
	
			product.setImage("products/" + uploadFileName);
		}
		//判断是不是商家用户，是则加uid
		String userType = (String) ServletActionContext.getRequest().getSession().getAttribute("userType");
		if("merchantUser".equals(userType)) {
			product.setUid(((User) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser")).getUid());
		}
		productService.save(product);
		return "saveSuccess";
	}

	// 删除商品的方法:
	public String delete() {
		// 根据id查询商品信息
		product = productService.findByPid(product.getPid());
		// 删除商品的图片:
		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + product.getImage());
		File file = new File(path);
		file.delete();
		// 删除数据库中商品记录:
		productService.delete(product);
		// 页面跳转
		return "deleteSuccess";
	}

	// 编辑商品的方法
	public String edit() {
		// 根据商品id查询商品信息
		product = productService.findByPid(product.getPid());
		// 查询所有二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转到编辑页面:
		return "editSuccess";
	}

	// 修改商品的方法
	public String update() throws Exception {
		// 将信息修改到数据库
//		product.setPdate(new Date());
		SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date2=temp.format(new Date());
		product.setPdate(temp.parse(date2));

		// 上传:
		if(upload != null ){
			String delPath = ServletActionContext.getServletContext().getRealPath(
					"/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		// 页面跳转
		return "updateSuccess";
	}
}
