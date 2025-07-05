package com.manager.account.dao;
//
//import com.manager.account.entity.Permisstions;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.math.BigInteger;
//import java.util.List;
//
//public interface PermisstionDAO extends JpaRepository<Permisstions, BigInteger> {
//    @Query(value = "select p.permission_code  from user_role ur, role_permissions r, permissions p " +
//            " where ur.role_id = r.roles_id and  r.permissions_id = p.id and ur.user_id =?1 ",
//            nativeQuery = true)
//    List<String> getListByUserId(BigInteger userId);
//
//    @Query(value = "select distinct p.path  from permissions p " +
//        "left join role_permissions r on p.id = r.permissions_id " +
//        "left join user_role ur on ur.role_id = r.roles_id " +
//        "where ur.user_id = ?1 " +
//        "  or p.type = 'FULL'",
//            nativeQuery = true)
//    List<String> getPath(BigInteger userId);
//
//    @Query(value = "select r.role_code  from users u, roles r, user_role ur where u.id = ur.user_id and r.id = ur.role_id " +
//            " and u.deleted = 0 and r.deleted =0 and u.id = ?1 ", nativeQuery = true)
//    List<String> getRoleByUserId(BigInteger userId);
//}
