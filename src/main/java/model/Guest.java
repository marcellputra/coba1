/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author USER
 */
public class Guest {

        private int id;
        private String name;
        private String purpose;
        private String message;
        private LocalDateTime visitTime;

        public Guest() {
        }

        public Guest(int id, String name, String purpose, String message, LocalDateTime visitTime) {
            this.id = id;
            this.name = name;
            this.purpose = purpose;
            this.message = message;
            this.visitTime = visitTime;
        }

        // Getter dan Setter
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getVisitTime() {
            return visitTime;
        }

        public void setVisitTime(LocalDateTime visitTime) {
            this.visitTime = visitTime;
        }
    }
