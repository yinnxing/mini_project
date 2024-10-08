package yin.com.stores.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import yin.com.stores.dto.request.StoreRequest;
import yin.com.stores.dto.response.StoreResponse;
import yin.com.stores.enums.Role;
import yin.com.stores.exception.AppException;
import yin.com.stores.exception.ErrorCode;
import yin.com.stores.mapper.StoreMapper;
import yin.com.stores.model.Store;
import yin.com.stores.model.User;
import yin.com.stores.repository.StoreProductRepository;
import yin.com.stores.repository.StoreRepository;
import yin.com.stores.repository.UserRepository;
import yin.com.stores.service.interf.StoreService;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class StoreServiceImp implements StoreService {
    StoreRepository storeRepository;
    StoreMapper storeMapper;
    UserRepository userRepository;

    @Override
    public List<StoreResponse> getStores() {
        List<Store> stores = storeRepository.findAllByDeleted(false);
        return stores.stream().map(storeMapper::toStoreResponse).toList();
    }

    @Override
    public StoreResponse createStore(StoreRequest request){
        Store store = storeMapper.toStore(request);
        store.setDeleted(false);
        storeRepository.save(store);
        return storeMapper.toStoreResponse(store);

    }

    @Override
    @Transactional
    public void deleteStore(String storeId) {
        Store store = storeRepository.findByStoreIdAndDeletedFalse(storeId).orElseThrow(
                () -> new AppException(ErrorCode.STORE_NOT_FOUND)
        );
        store.setDeleted(true);
        List<User> users = userRepository.findByStores_StoreIdAndRoleAndDeletedFalse(storeId, Role.EMPLOYEE.name());
        for(User user : users){
            user.setDeleted(true);
            userRepository.save(user);
        }
        User manager = userRepository.findManagerByStoreId(storeId);
        if(storeRepository.findStoresByUserIdAndDeletedFalse(manager.getId()) == null){
            manager.setDeleted(true);
            userRepository.save(manager);
        }


    }


}
