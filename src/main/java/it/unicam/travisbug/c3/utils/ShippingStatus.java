package it.unicam.travisbug.c3.utils;

public enum ShippingStatus {
    Pending,                //when the order is not confirmed and is in the cart
    ConfirmedShop,          //when the Client choose to retire it directly in the shop
    Confirmed,              //when the Client choose to send it in a locker
    ReadyForPickup,         //waiting for the Courier to retire in shop
    ReadyForClientPickup,   //waiting for the Client to retire in shop
    Shipping,               //when the courier has retired the package
    Delivered,              //when the courier has delivered the package
    Retired                 //when the Client has retired the package from the shop or locker
}
