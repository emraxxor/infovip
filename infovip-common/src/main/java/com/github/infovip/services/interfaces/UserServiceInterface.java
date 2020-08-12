package com.github.infovip.services.interfaces;

import java.util.List;


/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public interface UserServiceInterface<T> {

	public List<T> users(int offset, int limit);
	
	public T findUser(String userName, String password);
	
	public T findUserByEmail(String userEmail, String password);
	
	public T findUserByEmail(String mail);
	
	public T findUserByName(String name);
	
	public T userByNameAndPassword(String userName, String password);
	
	public T getUser(long id);
	
	public void modifyUserName(String userName, String newUserName);
	
	public List<T> removeUserById(Long uid);
	
	public void delete(T u);

	public T findByUserName(String userName);
	
	public T findById(Long id);
	
	public T save(T u);

	public <X> List<T> findAll(X pageable);

}
