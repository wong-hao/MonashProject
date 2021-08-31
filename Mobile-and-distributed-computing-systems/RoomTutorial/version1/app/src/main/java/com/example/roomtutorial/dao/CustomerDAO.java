package com.example.roomtutorial.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomtutorial.entity.Customer;

import java.util.List;

@Dao
public interface CustomerDAO {
    @Query("SELECT * FROM customer")
    List<Customer> getAll();
    @Query("SELECT * FROM customer WHERE uid = :customerId LIMIT 1")
    Customer findByID(int customerId);
    @Insert
    void insertAll(Customer... customers);
    @Insert
    long insert(Customer customer);
    @Delete
    void delete(Customer customer);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCustomers(Customer... customers);
    @Query("DELETE FROM customer")
    void deleteAll();
}


