package com.example.user_manage.service.imp;

import com.example.user_manage.dto.request.GroupReq;
import com.example.user_manage.entity.Group;
import com.example.user_manage.entity.User;
import com.example.user_manage.reponsitory.GroupRepo;
import com.example.user_manage.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImp implements GroupService {


    private final GroupRepo groupRepo;
    @Override
    public Group getById(Long id) {
        Optional<Group> group = groupRepo.findById(id);
        if(group.isEmpty()){
            throw  new RuntimeException("Khong tim thay group");
        }else{
            return group.get();
        }
    }

    @Override
    public List<Group> getAll() {
        return groupRepo.findAll();
    }

    @Override
    public Group createGroup(GroupReq request) {
        if(request.getGroupName() == null){
            throw new RuntimeException("Thieu du lieu");
        }
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        return groupRepo.save(group);
    }

    @Override
    public Group updateGroup(Long id, GroupReq request) {
        Group group = getById(id);
            group.setGroupName(request.getGroupName());
        return groupRepo.save(group);
    }

    @Override
    public void deleteGroup(Long id) {
        getById(id);
        groupRepo.deleteById(id);
    }
}
