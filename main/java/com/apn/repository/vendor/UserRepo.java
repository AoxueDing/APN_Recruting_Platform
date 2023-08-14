package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by JIALIN on 5/2/2017.
 */
public interface UserRepo extends PagingAndSortingRepository<User, Long> {
}
