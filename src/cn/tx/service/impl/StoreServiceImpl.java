package cn.tx.service.impl;

import java.util.Set;

import cn.tx.dao.OrderDetailDao;
import cn.tx.dao.OrderModelDao;
import cn.tx.dao.ProductDao;
import cn.tx.dao.StoreDao;
import cn.tx.model.OrderDetail;
import cn.tx.model.OrderModel;
import cn.tx.model.Product;
import cn.tx.model.Store;
import cn.tx.model.StoreDetail;
import cn.tx.query.StoreQuery;
import cn.tx.service.StoreService;

public class StoreServiceImpl extends BaseServiceImpl<Store, StoreQuery> implements StoreService {

	
	private StoreDao storeDao;
	
	private ProductDao productDao;
	
	
	private OrderModelDao orderModelDao;
	
	
	private OrderDetailDao orderDetailDao;
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	
	public void setOrderModelDao(OrderModelDao orderModelDao) {
		this.orderModelDao = orderModelDao;
	}
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
		this.baseDao = storeDao;
	}

	@Override
	public void updateInstock(Integer storeId, Integer productId,
			Integer productNum, Integer orderDetailId) {
		//查看要入库的仓库是否存在该商品
		Store store = storeDao.getObj(storeId);
		//获得订单的明细
		OrderDetail orderDetail = orderDetailDao.getObj(orderDetailId);
		//获得仓库明细
		Set<StoreDetail> details = store.getDetails();
		boolean isExist = false;
		for(StoreDetail sd : details){
			if(sd.getProduct().getProductId().intValue() == productId.intValue()){
				//修改仓库的明细
				sd.setNum(sd.getNum()+productNum);
				//修改订单详情的剩余数量
				orderDetail.setSurplus(orderDetail.getSurplus() - productNum);
				isExist = true;
				break;
			}
		}
		//如果仓库中没有要入库的商品，那么我们应该在仓库中插入一条明细，创建一个明细对象
		if(!isExist){
			StoreDetail sd = new StoreDetail();
			sd.setNum(productNum);
			Product product = productDao.getObj(productId);
			sd.setProduct(product);
			sd.setStoreId(storeId);
			details.add(sd);
		}
		
		//获得订单信息
		
		String orderId = orderDetail.getOrderId();
		//获得当前明细的所有订单
		OrderModel order = orderModelDao.getObj(new Integer(orderId));
		//获得这个订单下的所有明细
		Set<OrderDetail> details2 = order.getDetails();
		boolean isFinish = true;
		for(OrderDetail od : details2){
			if(od.getSurplus().intValue() != 0){
				order.setOrderState(2);
				isFinish = false;
				break;
			}
		}
		if(isFinish){
			order.setOrderState(3);
		}
		
		
		
		
	}
	
	

}
