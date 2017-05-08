package com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuonghq on 4/26/2017.
 */

public class ATM {

        private int id;

        private String created;

        @SerializedName("delete_flag")
        private boolean deleteFlag;

        private String address;

        private District district;


        public ATM(int id, String created, boolean deleteFlag, String address, District district) {
            this.id = id;
            this.created = created;
            this.deleteFlag = deleteFlag;
            this.address = address;
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public int getId() {
            return id;
        }

        public String getCreated() {
            return created;
        }

        public boolean isDeleteFlag() {
            return deleteFlag;
        }

        public District getDistrict() {
            return district;
        }

        @Override
        public String toString() {
            return "ATMInformation{" +
                    "id=" + id +
                    ", created='" + created + '\'' +
                    ", deleteFlag=" + deleteFlag +
                    ", district=" + district +
                    '}';
        }

}
