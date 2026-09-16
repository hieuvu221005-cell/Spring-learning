package com.example.user_manage.service.imp;

import com.example.user_manage.dto.request.UserReq;
import com.example.user_manage.entity.User;
import com.example.user_manage.reponsitory.UserRepo;
import com.example.user_manage.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {


    private final UserRepo userRepo;
    private final EntityManager entityManager;


    @Override
    public User getById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()){
            throw  new RuntimeException("Khong tim thay user");
        }else{
            return user.get();
        }
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User createUser(UserReq request) {
        if(request.getUserName() == null || request.getFirstName() == null || request.getLastName() == null || request.getAge() == null){
            throw new RuntimeException("Thieu du lieu");
        }
        User user = new User();
        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());

        return userRepo.save(user);
    }

    @Override
    public User updateUser(Long id, UserReq request) {

        User user = getById(id);
            user.setUserName(request.getUserName());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setAge(request.getAge());
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        getById(id);
        userRepo.deleteById(id);
    }

    @Override
    public List<User> filter(String userName, String firstName, String lastName, Long minAge, Long maxAge) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

        Root<User> root = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if(userName != null && !userName.isEmpty()){
            Predicate predicate = criteriaBuilder.like(root.get("userName"), "%" + userName + "%");
            predicates.add(predicate);
        }

        if(firstName != null && !firstName.isEmpty()){
            Predicate predicate = criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
            predicates.add(predicate);
        }

        if(lastName != null && !lastName.isEmpty()){
            Predicate predicate = criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%");
            predicates.add(predicate);
        }

        if(minAge != null){
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("age"),minAge);
            predicates.add(predicate);
        }

        if(maxAge != null){
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("age"),maxAge);
            predicates.add(predicate);
        }


        query.where(predicates.toArray(new Predicate[0]));
        List<User> userList = entityManager.createQuery(query).getResultList();
        return userList;
    }


    //luyen tap

    @Override
    @Transactional
    public void updateUserByFirstName() {
         userRepo.updateUserByFirstName();
    }

    @Override
    @Transactional
    public void deleteUserByFirstName() {
        userRepo.deleteUserByFirstName();
    }
}
