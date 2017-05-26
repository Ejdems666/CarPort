package org.cba.model.facade;

import org.cba.domain.AssemblyMaterial;
import org.cba.domain.User;
import org.cba.model.exception.WrongPasswordException;
import org.cba.parameter.ParsedParameters;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

/**
 * Created by Rares on 26-May-17.
 */
public class ProfileFacadeTest {
        private ProfileFacade facade = new ProfileFacade();
        private User loggedUser;

        @BeforeMethod
        public void setUp() throws Exception {
            loggedUser = User.find.byId(1);
        }

        @Test
        public void testChangePassword() throws Exception {
            System.out.println(loggedUser);
            try {
                facade.changePassword("wrong-pass", loggedUser, "", "");
            } catch (WrongPasswordException e ){
                Assert.assertEquals(e.getMessage(), "Old password does not match");
            }

        }

    }
