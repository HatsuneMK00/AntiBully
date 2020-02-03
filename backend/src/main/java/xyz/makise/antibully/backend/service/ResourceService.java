package xyz.makise.antibully.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.makise.antibully.backend.entity.Resource;
import xyz.makise.antibully.backend.mapper.ResourceMapper;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    ResourceMapper resourceMapper;

    public List<Resource> getAllResources() {
        List<Resource> resources = resourceMapper.getAllResource();
        for (Resource resource : resources) {
            resource.setRoles(resourceMapper.getNeededRolesOfResourceByResourceId(resource.getResourceId()));
        }
        return resources;
    }

}
