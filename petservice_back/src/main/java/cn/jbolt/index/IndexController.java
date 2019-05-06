package cn.jbolt.index;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;

import cn.jbolt.common.model.Article;
import cn.jbolt.common.model.Goods;
import cn.jbolt.common.model.Goodstype;
import cn.jbolt.common.model.Master;
import cn.jbolt.common.model.Phase;
import cn.jbolt.common.model.Selection;
import cn.jbolt.common.model.Service;
import cn.jbolt.common.model.Store;
import cn.jbolt.common.model.Vip;
import cn.jbolt.index.validator.ArticleValidator;
import cn.jbolt.index.validator.GoodsValidator;
import cn.jbolt.index.validator.SelectionValidator;
import cn.jbolt.index.validator.ServiceValidator;
import cn.jbolt.index.validator.VipValidator;
import cn.jbolt.service.ArticleService;
import cn.jbolt.service.GoodsService;
import cn.jbolt.service.GoodsTypeService;
import cn.jbolt.service.OrderService;
import cn.jbolt.service.PhaseService;
import cn.jbolt.service.SelectionService;
import cn.jbolt.service.ServiceService;
import cn.jbolt.service.UserService;
import cn.jbolt.service.VipService;
import cn.jbolt.service.bean.OrderPageBean;

/**
 * IndexController 指向系统访问首页
 * 
 * @author jbolt.cn
 * @email 909854136@qq.com
 * @date 2018年11月4日 下午9:02:52
 */
@Before({ IndexInterceptor.class, IndexPageInterceptor.class })
public class IndexController extends Controller {
	@Inject
	private GoodsService goodsService;
	@Inject
	private GoodsTypeService goodsTypeService;
	@Inject
	private ServiceService serviceService;
	@Inject
	private VipService vipService;
	@Inject
	private ArticleService articleService;
	@Inject
	private OrderService orderService;
	@Inject
	private UserService userService;
	@Inject
	private PhaseService phaseService;
	@Inject
	private SelectionService selectionService;

	/**
	 * 首页Action
	 */
	public void index() {
		Store store = getSessionAttr("session");
		if (store.getStoreType() == 2) {
			vipList();
		} else if (store.getStoreType() == 1) {
			goodsList();
		}
	}

	public void logout() {
		removeSessionAttr("session");
		redirect("/");
	}

	/**
	 * 商品相关
	 */

	public void goodsList() {
		int page = getAttrForInt("page");
		String keyWord = get("keyWord");
		Store store = getSessionAttr("session");
		Page<Goods> goodsPage = goodsService.getGoodsPage(page, keyWord, store.getId());
		set("viewId", 1).set("page", page).set("goodsList", goodsPage.getList())
				.set("totalPage", goodsPage.getTotalPage()).set("path", "/index/goodsList")
				.set("placeholder", "请输入商品名称...").set("keyWord", keyWord).render("/index.html");
	}

	public void goodsForm() {
		Goods goods = getModel(Goods.class);
		keepModel(Goods.class);
		List<Goodstype> goodsTypes = goodsTypeService.getGoodsTypes();
		set("viewId", 2).set("goodsTypes", goodsTypes).set("isAdd", goods.getId() == null).render("/index.html");
	}

	@Before(GoodsValidator.class)
	public void goodsAdd() {
		Goods goods = getAttr("goods");
		Store store = getSessionAttr("session");
		goods.setStoreId(store.getId());
		goodsService.addGoods(goods);
		List<Goodstype> goodsTypes = goodsTypeService.getGoodsTypes();
		removeAttr("goods").set("successMsg", "商品添加成功").set("isAdd", true).set("viewId", 2)
				.set("goodsTypes", goodsTypes).render("/index.html");
	}

	public void goodsDelete() {
		int goodsId = getParaToInt("id");
		goodsService.deleteGoods(goodsId);
		goodsList();
	}

	@Before(GoodsValidator.class)
	public void goodsUpdate() {
		Goods goods = getAttr("goods");
		goodsService.updateGoods(goods);
		goodsList();
	}

	/**
	 * 服务相关
	 */

	public void serviceList() {
		int page = getAttrForInt("page");
		String keyWord = get("keyWord");
		Store store = getSessionAttr("session");
		Page<Service> servicePage = serviceService.getServicePage(page, keyWord, store.getId());
		set("viewId", 3).set("page", page).set("serviceList", servicePage.getList())
				.set("totalPage", servicePage.getTotalPage()).set("path", "/index/serviceList")
				.set("placeholder", "请输入服务类型...").set("keyWord", keyWord).render("/index.html");
	}

	public void serviceForm() {
		Service service = getModel(Service.class);
		keepModel(Service.class);
		set("viewId", 4).set("isAdd", service.getId() == null).render("/index.html");
	}

	@Before(ServiceValidator.class)
	public void serviceAdd() {
		Service service = getAttr("service");
		Store store = getSessionAttr("session");
		service.setStoreId(store.getId());
		serviceService.addService(service);
		removeAttr("service").set("successMsg", "服务添加成功").set("isAdd", true).set("viewId", 4).render("/index.html");
	}

	public void serviceDelete() {
		int serviceId = getParaToInt("id");
		serviceService.deleteService(serviceId);
		serviceList();
	}

	@Before(ServiceValidator.class)
	public void serviceUpdate() {
		Service service = getAttr("service");
		serviceService.updateService(service);
		serviceList();
	}

	/**
	 * 会员卡相关
	 */

	public void vipList() {
		int page = getAttrForInt("page");
		String keyWord = get("keyWord");
		Page<Vip> vipPage = vipService.getVipPages(page, keyWord);
		set("viewId", 5).set("page", page).set("vipList", vipPage.getList()).set("totalPage", vipPage.getTotalPage())
				.set("path", "/index/vipList").set("placeholder", "请输入会员卡名称...").set("keyWord", keyWord)
				.render("/index.html");
	}

	public void vipForm() {
		Vip vip = getModel(Vip.class);
		keepModel(Vip.class);
		set("viewId", 6).set("isAdd", vip.getId() == null).render("/index.html");
	}

	@Before(VipValidator.class)
	public void vipAdd() {
		Vip vip = getAttr("vip");
		vipService.addVip(vip);
		removeAttr("vip").set("successMsg", "会员卡添加成功").set("isAdd", true).set("viewId", 6).render("/index.html");
	}

	public void vipDelete() {
		int vipId = getParaToInt("id");
		vipService.deleteVip(vipId);
		vipList();
	}

	@Before(VipValidator.class)
	public void vipUpdate() {
		Vip vip = getAttr("vip");
		vipService.updateVip(vip);
		vipList();
	}

	/**
	 * 文章相关
	 */

	public void articleList() {
		int page = getAttrForInt("page");
		String keyWord = get("keyWord");
		Store store = getSessionAttr("session");
		Page<Article> articlePage = articleService.getArticlePages(page, keyWord, store.getId());
		set("viewId", 7).set("page", page).set("articleList", articlePage.getList())
				.set("totalPage", articlePage.getTotalPage()).set("path", "/index/articleList")
				.set("placeholder", "请输入文章标题...").set("keyWord", keyWord).render("/index.html");
	}

	@Before(ArticleValidator.class)
	public void articleAdd() {
		Article article = getAttr("article");
		Store store = getSessionAttr("session");
		article.setStoreId(store.getId());
		articleService.addArticle(article);
		removeAttr("article").set("successMsg", "文章添加成功").set("isAdd", true).set("viewId", 8).render("/index.html");
	}

	public void articleDelete() {
		int articleId = getParaToInt("id");
		articleService.deleteArticle(articleId);
		articleList();
	}

	@Before(ArticleValidator.class)
	public void articleUpdate() {
		Article article = getAttr("article");
		articleService.updateArticle(article);
		articleList();
	}

	public void articleForm() {
		Article article = getModel(Article.class);
		keepModel(Article.class);
		set("viewId", 8).set("isAdd", article.getId() == null).render("/index.html");
	}

	/**
	 * 订单相关
	 */

	public void orderList() {
		int page = getAttrForInt("page");
		String keyWord = get("keyWord");
		Store store = getSessionAttr("session");
		int masterId = -1;
		if (!StrKit.isBlank(keyWord)) {
			Master master = userService.findMasterByName(keyWord);
			masterId = master == null ? 0 : master.getId();
		}
		OrderPageBean orderPage = orderService.getOrderPage(page, masterId, store.getId());
		set("viewId", 9).set("page", page).set("orderList", orderPage.getOrderBeans())
				.set("totalPage", orderPage.getTotalPage()).set("path", "/index/orderList")
				.set("placeholder", "请输入下单人姓名...").set("keyWord", keyWord).render("/index.html");
	}
	
	public void passOrder(){
		int orderId = getParaToInt("id");
		orderService.passOrder(orderId);
		orderList();
	}

	/**
	 * 宠物评选相关
	 */

	public void petList() {
		int page = getAttrForInt("page");
		int phaseNum = getParaToInt("keyWord", 0);
		Store store = getSessionAttr("session");
		List<Phase> phaseList = phaseService.getAllPhase(store.getId());
		if (phaseNum == 0 && phaseList.size() > 0) {
			phaseNum = phaseList.get(phaseList.size() - 1).getPhaseNum();
		}
		Page<Selection> selectionPage = selectionService.getSelectionPage(page, phaseNum, store.getId());
		set("viewId", 10).set("page", page).set("selectionList", selectionPage.getList())
				.set("totalPage", selectionPage.getTotalPage()).set("path", "/index/petList")
				.set("selectedPhaseNum", phaseNum).set("phaseList", phaseList).render("/index.html");
	}

	public void petForm() {
		Selection selection = getModel(Selection.class);
		boolean isAdd = false;
		Phase phase = null;
		if (selection.getId() == null) {
			isAdd = true;
			Store store = getSessionAttr("session");
			phase = phaseService.getUnderwayPhase(store.getId());
			set("phase", phase);
		} else {
			keepModel(Selection.class);
		}
		set("viewId", 11).set("isAdd", isAdd).render("/index.html");
	}

	@Before(SelectionValidator.class)
	public void petAdd() {
		Selection selection = getAttr("selection");
		Store store = getSessionAttr("session");
		selection.setStoreId(store.getId());
		selectionService.addSelectionPet(selection);
		Phase phase = phaseService.getUnderwayPhase(store.getId());
		set("phase", phase);
		removeAttr("selection").set("successMsg", "评选宠物添加成功").set("isAdd", true).set("viewId", 11)
				.render("/index.html");
	}

	@Before(SelectionValidator.class)
	public void petUpdate() {
		Selection selection = getAttr("selection");
		selectionService.updateSelectionPet(selection);
		petList();
	}

	public void petDelete() {
		int selectionId = getParaToInt("id");
		selectionService.deleteSelectionPet(selectionId);
		petList();
	}

	/**
	 * 评选周期相关
	 */

	public void selectionPhase() {
		Store store = getSessionAttr("session");
		Phase phase = phaseService.getUnderwayPhase(store.getId());
		set("viewId", 12).set("phase", phase).render("/index.html");
	}

	public void createPhase() {
		Store store = getSessionAttr("session");
		phaseService.createPhase(store.getId());
		selectionPhase();
	}

	public void endPhase() {
		Store store = getSessionAttr("session");
		phaseService.endPhase(store.getId());
		selectionPhase();
	}
}