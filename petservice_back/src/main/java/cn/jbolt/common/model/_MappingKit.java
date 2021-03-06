package cn.jbolt.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("article", "id", Article.class);
		arp.addMapping("goods", "id", Goods.class);
		arp.addMapping("goodstype", "id", Goodstype.class);
		arp.addMapping("master", "id", Master.class);
		arp.addMapping("orderform", "id", Orderform.class);
		arp.addMapping("pettype", "id", Pettype.class);
		arp.addMapping("phase", "id", Phase.class);
		arp.addMapping("selection", "id", Selection.class);
		arp.addMapping("service", "id", Service.class);
		arp.addMapping("store", "id", Store.class);
		arp.addMapping("vip", "id", Vip.class);
	}
}

