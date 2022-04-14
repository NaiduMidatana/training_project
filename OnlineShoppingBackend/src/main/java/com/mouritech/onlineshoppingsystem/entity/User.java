package com.mouritech.onlineshoppingsystem.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID",length = 64)
    private String userId;

    @Column(name = "USER_NAME")//,unique = true)
    //@UniqueUsername(message="UserName  is already registered")
    @NotEmpty
	@Size(min=2, message="User name should have at least 2 characters")
	private String userName;
    
    @Column(name = "EMAIL")
    @Email(message = "Not a valid email")
	private String userEmail;
    
    @Column(name = "CONTACT")
    @NotBlank(message = "Contact Number is required")
    @Size(min = 10, max = 10,message = "Invalid Number")
	private String userPhn;
    
    @Column(name = "ADDRESS")
    @NotEmpty
	private String userAddress;
    
    @Column(name = "PASSWORD")
    @NotEmpty
	@Size(min=8, message="Password should have at least 8 characters")
	private String password;
    @Column(name = "ENABLED")
    private boolean enabled;
 

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	

	
	
}
