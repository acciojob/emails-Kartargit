package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    int inboxSize = 0;
    int trashSize = 0;
     String emailId;
static class Inbox{
    Date date;
    String sender;
    String message;
    public Inbox(Date dt,String sender, String message){
        this.date = dt;
        this.sender = sender;
        this.message = message;
    }
}
   private ArrayList<Inbox> inboxes ;

    public Gmail(String emailId, int inboxCapacity){
        super.setEmailId(emailId);
        this.inboxCapacity = inboxCapacity;
        inboxes = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        Inbox newMail = new Inbox(date,sender,message);
        if(inboxSize<inboxCapacity){
            inboxes.add(newMail);
            inboxSize++;
        }
        else{
            inboxes.remove(0);
            inboxes.add(newMail);
            trashSize++;
        }
        return;
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        for(int i=0;i<inboxSize;i++){
            Inbox curr = inboxes.get(i);
            if(curr.message.equals(message)){
                inboxes.remove(i);
                inboxSize--;
                return;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        String message = inboxes.get(inboxSize - 1).message;
        return message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        String message = inboxes.get(0).message;
        return message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int i = 0,j=inboxSize-1;
        for( ;i<inboxSize;i++){
            if(inboxes.get(i).date.equals(start))break;
        }
        for( ;j>=i;j--){
            if(inboxes.get(j).date.equals(end))break;
        }
        return j-i+1;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inboxSize;
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return  trashSize;
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trashSize = 0;
        return ;
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
