package banger.action.service;


import banger.action.model.Car;
import banger.action.util.ProductNum;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CarService {
   void addProduct(HttpSession session,String carProductId);
    List<ProductNum> myCar(HttpSession session);
    int updateProductNum(HttpSession session,String carProductId,int carProductNum);
    Car selectMyproduct(HttpSession session, String carProductId);
    int removeProduct(HttpSession session,String carProductId);
}
