package com.huivip.holu.service;

import com.huivip.holu.model.SelectLabelValue;
import com.huivip.holu.model.User;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
@Path("/users")
public interface UserService {
    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    @GET
    @Path("{id}")
    User getUser(@PathParam("id") String userId);

    /**
     * Finds a user by their username.
     *
     * @param username the user's username used to login
     * @return User a populated user object
     */
    User getUserByUsername(@PathParam("username") String username);

    /**
     * Retrieves a list of all users.
     *
     * @return List
     */
    @GET
    List<User> getUsers();

    @GET
    @Path("slv")
    List<SelectLabelValue> getUsersLabelValue();

    /**
     * Saves a user's information
     *
     * @param user the user's information
     * @return updated user
     * @throws UserExistsException thrown when user already exists
     */
  /*  @POST
    User saveUser(User user) throws UserExistsException;*/

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    @DELETE
    void removeUser(String userId);

    @POST
    @Path("userLogin")
    User userLogin(@FormParam("username")String username,@FormParam("password") String password);

    @POST
    @Path("signup")
    User signup(@FormParam("loginCode") String loginCode,
                @FormParam("userName")String userName,
                @FormParam("password")String password,
                @FormParam("companyId")String companyId);

   /* @GET
    @Path("{userId}/task")
    List<Task> myTask(@PathParam("userId") String userId);*/


}
