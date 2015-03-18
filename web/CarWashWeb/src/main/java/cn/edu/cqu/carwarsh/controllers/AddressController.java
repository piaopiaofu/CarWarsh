package cn.edu.cqu.carwarsh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.cqu.carwarsh.domains.Address;
import cn.edu.cqu.carwarsh.domains.Customer;
import cn.edu.cqu.carwarsh.services.AddressService;
import cn.edu.cqu.carwarsh.services.CustomerService;
import cn.edu.cqu.carwarsh.vos.JSONResult;
/**
 * 用户常用洗车地址服务端接口
 * @author liuji
 *
 */
@RestController
public class AddressController {
	/**
	 * 用于输出日志
	 */
	private static Logger logger = LoggerFactory
			.getLogger(AddressController.class);
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;
	/**
	 * 添加洗车地址
	 * @param mobile 手机号
	 * @param address 地址
	 * @return
	 */
	@RequestMapping(value = "/address/add.do")
	public JSONResult addAddress(String mobile,String address)
	{
		JSONResult result = new JSONResult();
		try {
			Customer customer = customerService.findByMobile(mobile);
			Address addr = new Address();
			addr.setCustomer(customer);
			addr.setDetailAddress(address);
			addressService.add(addr);
			result.setMsg("添加地址成功");
			result.setState(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("手机号错误");
			result.setState(true);
		}
		return result;
	}
	
}
