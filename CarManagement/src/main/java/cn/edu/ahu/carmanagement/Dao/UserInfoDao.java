package cn.edu.ahu.carmanagement.Dao;

import cn.edu.ahu.carmanagement.Dao.Model.UserModel;
import cn.edu.ahu.carmanagement.Domain.SchoolCarRegister;
import cn.edu.ahu.carmanagement.Domain.UserInfo;
import cn.edu.ahu.carmanagement.Domain.WholeRouteRecord;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: FZC
 * @UpdateBy: Alex
 * @Despriction: 微信小程序app接口
 * @Date:Created in 15:33 2019/4/25
 */
@Repository
public class UserInfoDao {

    @PersistenceContext
    private EntityManager em;

    public List<UserModel> findByAccountAndPassword(String account,String password){

        String dataSql = "select user,car " +
                " from UserInfo user " +
                " left join SchoolCarRegister car on user.plateNum = car.plateNum " +
                " where user.account = :account and user.password = :password";

        List<Object[]> list = em.createQuery(dataSql,Object[].class)
                .setParameter("account",account)
                .setParameter("password",password)
                .getResultList();

        List<UserModel> userModelList = new ArrayList<>(list.size());

        list.forEach(e -> userModelList.add(convert(e)));

        return userModelList;
    }

    private UserModel convert(Object[] e) {
        return new UserModel(
                (UserInfo) e[0],
                (SchoolCarRegister) e[1]
        );
    }

    public List<UserInfo> findLikeAccount(String account){

        String dataSql = "select ui " +
                " from UserInfo ui " +
                " where ui.account like :account " +
                "And ui.state=:state" ;

        List<UserInfo> list = em.createQuery(dataSql,UserInfo.class)
                .setParameter("account","%" + account +"%")
                .setParameter("state","启用")
                .getResultList();
        return list;
    }

    public List<UserInfo> findAll(){

        String dataSql = "select ui " +
                " from UserInfo ui " +
                " where ui.state=:state ";

        List<UserInfo> list = em.createQuery(dataSql,UserInfo.class)
                .setParameter("state","启用")
                .getResultList();
        return list;
    }
}
