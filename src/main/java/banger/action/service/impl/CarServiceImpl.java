package banger.action.service.impl;

import banger.action.dao.CarDAO;
import banger.action.model.Car;
import banger.action.model.User;
import banger.action.service.CarService;
import banger.action.util.ProductNum;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;

@Service
public class CarServiceImpl implements CarService{
  @Resource
  private CarDAO carDAO;
  //add  product to car
  public  void addProduct(HttpSession session, String carProductId){
  //  User user=(User) session.getAttribute("user");
//    String userId=user.getUserId();
    String carid = UUID.randomUUID().toString().replace("-", "");
    Car car=new Car();
    car.setCarBuyerId("110");
    car.setCarId(carid);
    car.setCarProductId(carProductId);
    car.setCarProductNum(1);
    car.setCarProductPrice(1.0);
    car.setCarStatus(0);
    carDAO.insertProduct(car);
  }
  public List<ProductNum> myCar(HttpSession session)
  {
    User user=(User) session.getAttribute("user");
    String userId=user.getUserId();
    List<Car> carList=carDAO.selectUserCar(userId,0);

    List<ProductNum> productNums=new ArrayList<>();
    for(Iterator iter = carList.iterator(); iter.hasNext();){
      ProductNum productNum=new ProductNum();
      Car car=(Car)iter.next();
      productNum.setProductId(car.getCarProductId());
      productNum.setCarProductNum(car.getCarProductNum());
      productNums.add(productNum);
    }
    return  productNums;
  }
  public int updateProductNum(HttpSession session,String carProductId,int carProductNum){
    User user=(User) session.getAttribute("user");
   // String userId=user.getUserId();
    String userId="110";
    double price=carProductNum*1.0;
    return carDAO.updateNum(carProductId,carProductNum,price,userId);
  }

  @Override
  public Car selectMyproduct(HttpSession session, String carProductId) {
    User user=(User) session.getAttribute("user");
    //String userId=user.getUserId();
    String userId="110";
    Car car=carDAO.selectCarproduct(carProductId,0,userId);
    return car;
  }
}
