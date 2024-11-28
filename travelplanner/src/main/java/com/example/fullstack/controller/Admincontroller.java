package com.example.fullstack.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullstack.model.Users;
import com.example.fullstack.model.bookings;
import com.example.fullstack.model.fooditems;
import com.example.fullstack.model.hotels;
import com.example.fullstack.model.restaurants;
import com.example.fullstack.model.rooms;
import com.example.fullstack.repository.userdetails;

import jakarta.servlet.http.HttpSession;

import com.example.fullstack.repository.bookingsdetails;
import com.example.fullstack.repository.fooddetails;
import com.example.fullstack.repository.hoteldetails;
import com.example.fullstack.repository.restaurantdetails;
import com.example.fullstack.repository.roomdetails;


@Controller
public class Admincontroller {

    @Autowired
    userdetails userrepo;

    @Autowired
    hoteldetails hotelrepo;

    @Autowired
    restaurantdetails restaurantrepo;
    
    @Autowired
    fooddetails foodItemRepo;
    
    @Autowired
    roomdetails roomRepo;

    @Autowired
    bookingsdetails bookingrepo;

    @GetMapping("/")
    public String home() {
        return "home"; // Returns home.html
    }

    @GetMapping("/userregister")
    public String userregister() {
        return "register";
    }

    @GetMapping("/hotelregister")
    public String hotelregister() {
        return "hotelregister";
    }

    @GetMapping("/restaurantregister")
    public String restaurantregister() {
        return "restaurentregister";
    }

    @GetMapping("/login")
    public String loginpage() {
        return "login";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    

    @PostMapping("/logindata")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("category") String category,
                        Model model,
                        HttpSession session) {

        if ("users".equals(category)) {
           
            Users user = userrepo.findByEmail(email);
            if (user == null || !user.getPassword().equals(password)) {
                model.addAttribute("status", "Invalid email or password for Users");
                return "login";
            }

            
            session.setAttribute("loggedInUser", email);

            return "redirect:/search";

        } else if ("hotels".equals(category)) {
            // Check in Hotels table
            hotels hotel = hotelrepo.findByEmail(email);
            if (hotel == null || !hotel.getPassword().equals(password)) {
                model.addAttribute("status", "Invalid email or password for Hotels");
                return "login";
            }

            // Store the hotel's owner email in the session
            session.setAttribute("loggedInOwner", email);
            session.setAttribute("loggedcategory", category);


            return "redirect:/hoteldashboard";

        } else if ("restaurants".equals(category)) {
            // Check in Restaurants table
            restaurants restaurant = restaurantrepo.findByEmail(email);
            if (restaurant == null || !restaurant.getPassword().equals(password)) {
                model.addAttribute("status", "Invalid email or password for Restaurants");
                return "login";
            }

            // Store the restaurant owner's email in the session
            session.setAttribute("RestaurantName", restaurant.getRestaurantName());
            session.setAttribute("loggedInOwner", email);
            session.setAttribute("loggedcategory", category);

            return "redirect:/restaurantdashboard";

        } else {
            model.addAttribute("status", "Invalid category selected");
            return "login";
        }
    }


    @PostMapping("/usersregister")
    public String userRegister(Users user, Model model) {
        userrepo.save(user);
        model.addAttribute("status", "Registration successful");
        return "login";
    }
    
    @PostMapping("/restaurantdata")
    public String restaurantRegister(restaurants restaurant, Model model) {
    	restaurantrepo.save(restaurant);
        model.addAttribute("status", "Registration successful");
        return "login";
    }
    
    @PostMapping("/hotelregisterdata")
    public String hotelregisterdata(hotels hotel,Model model) {
    	hotelrepo.save(hotel);
    	model.addAttribute("status","Registration successfull");
    	return "login";
    }
    
    
    @GetMapping("/userdashboard")
    public String userdashboard() {
    	return "dashboardpage";
    }
    
    @GetMapping("/hoteldashboard")
    public String hoteldashboard(Model model, HttpSession session) {
        // Redirect to login if no session
        if (session.getAttribute("loggedInOwner") == null) {
            return "redirect:/login";
        }

        // Fetch Rooms uploaded by the logged-in owner
        String loggedInOwner = (String) session.getAttribute("loggedInOwner");
        List<rooms> Rooms = roomRepo.findByUploadedBy(loggedInOwner);

        // Pass data to the template
        model.addAttribute("loggedInOwner", loggedInOwner);
        model.addAttribute("loggedcategory", session.getAttribute("loggedcategory"));
        model.addAttribute("Rooms", Rooms);

        return "hotelownerdashboard"; // Template file name
    }
    
    
    @GetMapping("/rooms")
    public String viewRooms(Model model, HttpSession session) {
        // Ensure the user is logged in
        if (session.getAttribute("loggedInOwner") == null) {
            return "redirect:/login"; // Redirect to login if session is missing
        }

        // Fetch the list of rooms for the logged-in owner
        String ownerEmail = (String) session.getAttribute("loggedInOwner");
        List<rooms> roomList = roomRepo.findByUploadedBy(ownerEmail);

        // Add rooms to the model
        model.addAttribute("rooms", roomList);

        return "rooms"; // Return the rooms.html template
    }


    
    
    @GetMapping("/restaurantdashboard")
    public String restaurantdashboard(Model model,HttpSession session) {
        if (session.getAttribute("loggedInOwner") == null) {
            // Redirect to the login page if no session is found
            return "redirect:/login";
        }
    	List<fooditems> foodItems=foodItemRepo.findByUploadedBy((String)session.getAttribute("loggedInOwner"));
    	model.addAttribute("loggedInOwner",session.getAttribute("loggedInOwner"));
    	model.addAttribute("loggedcategory",session.getAttribute("loggedcategory"));
    	model.addAttribute("RestaurantName",session.getAttribute("RestaurantName"));
    	model.addAttribute("menuItems",foodItems);
    	return "restaurent";
    }
    @GetMapping("/addroom")
    public String addroom(Model model,HttpSession session) {
    	if (session.getAttribute("loggedInOwner") == null) {
            model.addAttribute("status", "Please log in to access this page.");
            return "login"; 
        }
    	return "addroom";
    }
    @GetMapping("/addfooditem")
    public String addFoodItemForm(Model model, HttpSession session) {
        if (session.getAttribute("loggedInOwner") == null) {
            model.addAttribute("status", "Please log in to access this page.");
            return "login"; 
        }
        return "addfooditem"; 
    }   
    
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        foodItemRepo.deleteById(id); // Delete the item
        return "redirect:/restaurantdashboard"; // Redirect back to the menu page
    }

    
    @PostMapping("/addingfooditem")
    public String addFoodItem(@ModelAttribute fooditems foodItem,RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        if (session.getAttribute("loggedInOwner") == null) {
            model.addAttribute("status", "Please log in to access this page.");
            return "login";
        }

       
        String ownerEmail = (String) session.getAttribute("loggedInOwner");
        foodItem.setUploadedBy(ownerEmail);

       
        foodItemRepo.save(foodItem);

		redirectAttributes.addFlashAttribute("status", "Food item added successfully!");
        return "redirect:/addfooditem";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (session.getAttribute("loggedInOwner") == null) {
            model.addAttribute("status", "Please log in to access this page.");
            return "login"; // Redirect to login page if user is not logged in
        }
        session.setAttribute("updateid", id);

        // Retrieve the food item by ID
        fooditems foodItem = foodItemRepo.findById(id).orElse(null);
        if (foodItem == null) {
            model.addAttribute("status", "Food item not found");
            return "redirect:/restaurantdashboard"; // Redirect if food item not found
        }

        model.addAttribute("foodItem", foodItem);
        return "editfooditem";
    }

    @GetMapping("/hotelinfo/{id}")
    public String showhotelinfo(@PathVariable("id") Long id,Model model,HttpSession session){
        hotels hotel=hotelrepo.findById(id).orElse(null);
        if (hotel == null){
            model.addAttribute("status", "hotel not found");
            return "redirect:/search";
        }
        String email = hotel.getEmail();
        List<rooms> roomsList=roomRepo.findByUploadedBy(email);
        model.addAttribute("rooms", roomsList);
        return "roomsdetails";
    }

    @GetMapping("/restaurantinfo/{id}")
    public String restauranthotelinfo(@PathVariable("id") Long id,Model model,HttpSession session){
        restaurants restaurant=restaurantrepo.findById(id).orElse(null);
        if (restaurant == null){
            model.addAttribute("status", "restaurant not found");
            return "redirect:/search";
        }
        String email = restaurant.getEmail();
        List<fooditems> foodList=foodItemRepo.findByUploadedBy(email);
        model.addAttribute("items", foodList);
        return "fooditemdetails";
    }



    
    @PostMapping("/update")
    public String updateFoodItem(@ModelAttribute fooditems foodItem, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("loggedInOwner") == null) {
            redirectAttributes.addFlashAttribute("status", "Please log in to access this page.");
            return "redirect:/login"; // Redirect to login if the user is not logged in
        }

        // Retrieve the food item by ID
        fooditems existingFoodItem = foodItemRepo.findById((Long)session.getAttribute("updateid")).orElse(null);
        if (existingFoodItem == null) {
            redirectAttributes.addFlashAttribute("status", "Food item not found");
            return "redirect:/restaurantdashboard"; // Redirect if food item not found
        }

        // Set the updated fields (some fields may not be updated, so we need to handle them properly)
        existingFoodItem.setFoodItemName(foodItem.getFoodItemName());
        existingFoodItem.setFoodType(foodItem.getFoodType());
        existingFoodItem.setCost(foodItem.getCost());
        existingFoodItem.setImageUrl(foodItem.getImageUrl());

        // Save the updated food item
        foodItemRepo.save(existingFoodItem);

        redirectAttributes.addFlashAttribute("status", "Food item updated successfully!");
        return "redirect:/restaurantdashboard";
    }


   @PostMapping("/bookingroom")
    public String bookingroom(
            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam("noOfGuests") Integer noOfGuests,
            Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        // Check if the user is logged in
        if (session.getAttribute("loggedInUser") == null) {
            model.addAttribute("status", "Please log in to access this page.");
            return "login";
        }

        // Retrieve the room details using the ID stored in the session
        Long roomId = (Long) session.getAttribute("bookid");
        rooms bookingRoom = roomRepo.findById(roomId).orElse(null);
        if (bookingRoom == null) {
            redirectAttributes.addFlashAttribute("status", "Room not found.");
            return "redirect:/rooms";
        }
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        // Retrieve the logged-in user's email
        String bookedBy = (String) session.getAttribute("loggedInUser");

        // Retrieve the hotel owner's email from the room details
        String hotelOwner = bookingRoom.getUploadedBy();

        Double totalPrice = daysBetween * bookingRoom.getPricePerNight();
        // Create a new booking object
        bookings newBooking = new bookings(
                bookingRoom.getRoomNo(),
                bookingRoom.getRoomType(),
                noOfGuests,
                checkInDate,
                checkOutDate,
                bookedBy,
                hotelOwner,
                totalPrice,
                "Pending" // Status defaults to "Pending"
        );

        // Save the booking
        bookingrepo.save(newBooking);

        // Add a success message and redirect
        redirectAttributes.addFlashAttribute("status", "Room booked successfully! Your booking is pending approval.");
        return "redirect:/search";
    }


    @GetMapping("/bookings")
    public String viewBookings(Model model, HttpSession session) {
        // Check if the user is logged in
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            model.addAttribute("status", "Please log in to access your bookings.");
            return "login";  // Redirect to login if not logged in
        }

        // Fetch the user's bookings
        List<bookings> userBookings = bookingrepo.findByBookedBy(loggedInUser);

        // Add the bookings to the model
        model.addAttribute("bookings", userBookings);

        // Return the bookings view
        return "bookings";
    }

    
    
    @PostMapping("/addingroom")
    public String addRoom(@ModelAttribute rooms room, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        // Check if the owner is logged in
        if (session.getAttribute("loggedInOwner") == null) {
            model.addAttribute("status", "Please log in to access this page.");
            return "login";
        }

        // Retrieve the logged-in owner's email
        String ownerEmail = (String) session.getAttribute("loggedInOwner");

        // Set the uploadedBy field to the owner's email (if needed)
        room.setUploadedBy(ownerEmail);

        // Save the room object to the database
        roomRepo.save(room);

        // Add a success message to be displayed
        redirectAttributes.addFlashAttribute("status", "Room added successfully!");

        // Redirect back to the "add room" form page
        return "redirect:/addroom"; // or any other page you want to redirect to
    }
    
    @PostMapping("/searchtrip")
    public String searchtrip(@RequestParam("fromLocation") String from, 
                            @RequestParam("toLocation") String to, 
                            Model model,HttpSession session) {

        if(session.getAttribute("loggedInUser")==null){
            model.addAttribute("status", "Please log in to access this page.");
            return "login";

        }               
        // Assuming you have a custom query method or findAll method to fetch hotels
        List<hotels> hotelList = hotelrepo.findBycity(to); // Or a filtered method like findByFromLocationAndToLocation(from, to)
        
        // Print the hotel list in the console
        // System.out.println("Hotel List:");
        // for (hotels hotel : hotelList) {
        //     System.out.println(hotel); // This will call the toString() method automatically
        // }
        
        // Get all restaurants (if needed)
        List<restaurants> restaurantList = restaurantrepo.findBycity(to);
        
        // Add the hotel list and restaurant list to the model with distinct attribute names
        model.addAttribute("places", hotelList); 
        model.addAttribute("restaurants", restaurantList);
        
        return "search"; // Return the search view
    }

    @GetMapping("/book/{id}")
    public String bookRoom(@PathVariable("id") Long id, Model model,HttpSession session) {
        // Fetch the room details by ID
        rooms room = roomRepo.findById(id).orElse(null);

        // Handle case when the room is not found
        if (room == null) {
            model.addAttribute("errorMessage", "Room not found");
            return "redirect:/rooms"; // Redirect back to rooms page or show an error
        }
        session.setAttribute("bookid", id);
        // Add room details to the model to pre-fill the form
        model.addAttribute("room", room);

        // Render the booking form page
        return "bookingForm"; // Name of the Thymeleaf template for the booking form
    }


    @GetMapping("/ownerbookings")
    public String viewownerBookings(Model model, HttpSession session) {
        // Check if the hotel owner is logged in
        String loggedInOwner = (String) session.getAttribute("loggedInOwner");
        if (loggedInOwner == null) {
            return "redirect:/login"; // Redirect to login page if not logged in
        }

        // Fetch the bookings related to the logged-in hotel owner
        List<bookings> bookingsList = bookingrepo.findByHotelOwner(loggedInOwner); // Adjust based on your repository method

        // Add the bookings list to the model
        model.addAttribute("bookings", bookingsList);

        // Return the bookings page template
        return "managebooking"; // This refers to the bookings.html page
    }

    @PostMapping("/acceptBooking/{id}")
    public String acceptBooking(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookings booking = bookingrepo.findById(id).orElse(null);
        if (booking != null) {
            // Update status to "Booked"
            booking.setStatus("Booked");
            bookingrepo.save(booking);
            redirectAttributes.addFlashAttribute("status", "Booking confirmed.");
        } else {
            redirectAttributes.addFlashAttribute("status", "Booking not found.");
        }
        return "redirect:/ownerbookings"; // Redirect back to the bookings page
    }

    @PostMapping("/rejectBooking/{id}")
    public String rejectBooking(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookings booking = bookingrepo.findById(id).orElse(null);
        if (booking != null) {
            // Update status to "Cancelled"
            booking.setStatus("Cancelled");
            bookingrepo.save(booking);
            redirectAttributes.addFlashAttribute("status", "Booking cancelled.");
        } else {
            redirectAttributes.addFlashAttribute("status", "Booking not found.");
        }
        return "redirect:/ownerbookings"; // Redirect back to the bookings page
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session to log out the user
        session.invalidate(); 

        // Redirect to the login page after logging out
        return "redirect:/login";
    }



    
}
