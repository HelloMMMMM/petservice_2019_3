package cn.jbolt.api;

import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

import cn.jbolt.common.model.Article;
import cn.jbolt.common.model.Goods;
import cn.jbolt.common.model.Master;
import cn.jbolt.common.model.Selection;
import cn.jbolt.common.model.Service;
import cn.jbolt.common.model.Store;
import cn.jbolt.common.model.Vip;
import cn.jbolt.service.ArticleService;
import cn.jbolt.service.GoodsService;
import cn.jbolt.service.OrderService;
import cn.jbolt.service.PetTypeService;
import cn.jbolt.service.SelectionService;
import cn.jbolt.service.ServiceService;
import cn.jbolt.service.StoreService;
import cn.jbolt.service.UserService;
import cn.jbolt.service.VipService;
import cn.jbolt.service.bean.OrderPageBean;

@Clear
public class ApiController extends Controller {
	@Inject
	private StoreService storeService;
	@Inject
	private GoodsService goodsService;
	@Inject
	private ServiceService serviceService;
	@Inject
	private ArticleService articleService;
	@Inject
	private UserService userService;
	@Inject
	private SelectionService selectionService;
	@Inject
	private OrderService orderService;
	@Inject
	private PetTypeService petTypeService;
	@Inject
	private VipService vipService;

	/**
	 * 更换服务器修改这里
	 */
	private String baseUrl = "http://192.168.0.6/";

	public void getStoreList() {
		String keyWord = get("keyWord");
		int page = getParaToInt("page", 1);
		Page<Store> pages = storeService.getStorePage(keyWord, page);
		StoreListBean storeListBean = new StoreListBean();
		List<Store> stores = pages.getList();
		for (Store store : stores) {
			store.setUserName(null);
			store.setPassword(null);
		}
		storeListBean.setStoreList(stores);
		storeListBean.setCode(1);
		renderJson(storeListBean);
	}

	public void starStore() {
		int storeId = getParaToInt("id");
		storeService.starStore(storeId);
		ResponseBean responseBean = new ResponseBean();
		responseBean.setCode(1);
		renderJson(responseBean);
	}

	public void getGoodsList() {
		int page = getParaToInt("page", 1);
		int storeId = getParaToInt("storeId", 0);
		Page<Goods> pages = goodsService.getGoodsPage(page, null, storeId);
		GoodsListBean goodsListBean = new GoodsListBean();
		List<Goods> goodsList = pages.getList();
		for (Goods goods : goodsList) {
			goods.setGoodsImg(baseUrl + goods.getGoodsImg());
		}
		goodsListBean.setGoodsList(goodsList);
		goodsListBean.setCode(1);
		renderJson(goodsListBean);
	}

	public void getServiceList() {
		int page = getParaToInt("page", 1);
		int storeId = getParaToInt("storeId", 0);
		Page<Service> pages = serviceService.getServicePage(page, null, storeId);
		ServiceListBean serviceListBean = new ServiceListBean();
		serviceListBean.setServiceList(pages.getList());
		serviceListBean.setCode(1);
		renderJson(serviceListBean);
	}

	public void getArticleList() {
		int page = getParaToInt("page", 1);
		int storeId = getParaToInt("storeId", 0);
		Page<Article> pages = articleService.getArticlePages(page, null, storeId);
		ArticleListBean articleListBean = new ArticleListBean();
		articleListBean.setArticleList(pages.getList());
		articleListBean.setCode(1);
		renderJson(articleListBean);
	}

	public void getSelectionPetList() {
		int page = getParaToInt("page", 1);
		int phaseNum = getParaToInt("phaseNum", 0);
		int storeId = getParaToInt("storeId", 0);
		Page<Selection> pages = selectionService.getSelectionPage(page, phaseNum, storeId);
		SelectionPetListBean selectionPetListBean = new SelectionPetListBean();
		selectionPetListBean.setCode(1);
		List<Selection> selections = pages.getList();
		for (Selection selection : selections) {
			selection.setPetImg(baseUrl + selection.getPetImg().replace("\\", "/"));
		}
		selectionPetListBean.setSelectionList(pages.getList());
		renderJson(selectionPetListBean);
	}

	public void getSelectionStarList() {
		int storeId = getParaToInt("storeId", 0);
		List<Selection> selections = selectionService.getAllSelectionStar(storeId);
		for (Selection selection : selections) {
			selection.setPetImg(baseUrl + selection.getPetImg().replace("\\", "/"));
		}
		SelectionPetListBean selectionPetListBean = new SelectionPetListBean();
		selectionPetListBean.setCode(1);
		selectionPetListBean.setSelectionList(selections);
		renderJson(selectionPetListBean);
	}

	public void starSelectionPet() {
		int selectionId = getParaToInt("selectionId", 0);
		selectionService.starSelectionPet(selectionId);
		ResponseBean responseBean = new ResponseBean();
		responseBean.setCode(1);
		renderJson(responseBean);
	}

	public void registerMaster() {
		UploadFile uploadFile = getFile();
		String absolutePath = uploadFile.getFile().getAbsolutePath();
		String petImg = absolutePath.substring(absolutePath.indexOf("upload")).replace("\\", "/");
		String userName = get("userName");
		String password = get("password");
		String masterName = get("masterName");
		String masterAddress = get("masterAddress");
		String masterPhone = get("masterPhone");
		String petName = get("petName");
		String petKind = get("petKind");
		String petType = get("petType");
		int petAge = getParaToInt("petAge", 0);
		userService.registerMaster(userName, password, masterName, masterAddress, masterPhone, petName, petKind,
				petType, petImg, petAge);
		ResponseBean responseBean = new ResponseBean();
		responseBean.setCode(1);
		renderJson(responseBean);
	}

	public void masterLogin() {
		String userName = get("userName");
		String password = get("password");
		Master master = userService.masterLogin(userName, password);
		MasterLoginBean masterLoginBean = new MasterLoginBean();
		if (master == null) {
			masterLoginBean.setCode(0);
			masterLoginBean.setMsg("用户名或密码错误");
		} else {
			masterLoginBean.setCode(1);
			master.setUserName(null);
			master.setPassword(null);
			master.setPetImg(baseUrl + master.getPetImg());
			masterLoginBean.setMaster(master);
		}
		renderJson(masterLoginBean);
	}

	public void getMasterInfo() {
		int masterId = getParaToInt("id");
		Master master = userService.findMasterById(masterId);
		master.setPetImg(baseUrl + master.getPetImg());
		MasterLoginBean masterLoginBean = new MasterLoginBean();
		masterLoginBean.setCode(1);
		master.setUserName(null);
		master.setPassword(null);
		masterLoginBean.setMaster(master);
		renderJson(masterLoginBean);
	}

	public void updatePetImg() {
		UploadFile uploadFile = getFile();
		int masterId = getParaToInt("id");
		String absolutePath = uploadFile.getFile().getAbsolutePath();
		String petImg = absolutePath.substring(absolutePath.indexOf("upload")).replace("\\", "/");
		userService.updatePetImg(masterId, petImg);
		ResponseBean responseBean = new ResponseBean();
		responseBean.setCode(1);
		renderJson(responseBean);
	}

	public void updateMasterInfo() {
		int masterId = getParaToInt("id");
		String masterName = get("masterName");
		String masterAddress = get("masterAddress");
		String masterPhone = get("masterPhone");
		String petName = get("petName");
		String petKind = get("petKind");
		String petType = get("petType");
		int petAge = getParaToInt("petAge", 0);
		String password=get("password");
		userService.updateMasterInfo(masterId, masterName, masterAddress, masterPhone, petName, petKind, petType,
				petAge,password);
		ResponseBean responseBean = new ResponseBean();
		responseBean.setCode(1);
		renderJson(responseBean);
	}

	public void getPetType() {
		PetTypeListBean petTypeListBean = new PetTypeListBean();
		petTypeListBean.setCode(1);
		petTypeListBean.setPetTypeList(petTypeService.getAllPetType());
		renderJson(petTypeListBean);
	}

	public void createOrder() {
		int orderType = getParaToInt("orderType");
		int masterId = getParaToInt("masterId");
		int storeId = getParaToInt("storeId");
		int serviceId = getParaToInt("serviceId", 0);
		int goodsId = getParaToInt("goodsId", 0);
		orderService.createOrder(orderType, masterId, storeId, serviceId, goodsId);
		ResponseBean responseBean = new ResponseBean();
		responseBean.setCode(1);
		renderJson(responseBean);
	}

	public void getOrderList() {
		int page = getParaToInt("page", 1);
		int masterId = getParaToInt("id");
		OrderPageBean orderPageBean = orderService.getOrderPage(page, masterId, -1);
		OrderListBean orderListBean = new OrderListBean();
		orderListBean.setCode(1);
		orderListBean.setOrderBeans(orderPageBean.getOrderBeans());
		renderJson(orderListBean);
	}

	public void getVipList() {
		int page = getParaToInt("page", 1);
		VipListBean vipListBean = new VipListBean();
		Page<Vip> pages = vipService.getVipPages(page, null);
		vipListBean.setVips(pages.getList());
		vipListBean.setCode(1);
		renderJson(vipListBean);
	}

	public void buyVip() {
		int masterId = getParaToInt("id");
		int vipId = getParaToInt("vipId");
		userService.buyVip(masterId, vipId);
		ResponseBean responseBean = new ResponseBean();
		responseBean.setCode(1);
		renderJson(responseBean);
	}
}
