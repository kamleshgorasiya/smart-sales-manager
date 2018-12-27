package com.smart.sales.manager.test.service;


import org.junit.Before;
import org.junit.Test;
import com.smart.sales.manager.model.User;
import com.smart.sales.manager.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * 
 * @author kamlesh
 * @Date: 26-Dec-2018
 * @Gitrepo :
 */

public class UserServiceTest {



    private UserService userService;
 

    @Before
    public void setUp() {
        initMocks(this);
               
    }

    @Test
    public void testFindUserUsername() {
        // Setup
        final String email = "admin";

        // Run the test
        final User result = userService.findOne(email);

        // Verify the results
        assertEquals(email, result.getUsername());
    }

    
}
