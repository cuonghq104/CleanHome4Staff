package com.example.cuonghq.cleanhome4staff.commons.utils;


import android.support.v4.app.Fragment;

import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.OrderAPIResponse;


/**
 * Created by Cuonghq on 4/25/2017.
 */

public class Event {

    public static class SelectedOrderEvent{
        private int position;

        public SelectedOrderEvent(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }

    public static class TabSelectedEvent{
        private int position;

        public TabSelectedEvent(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }

    public static class CompleteOrderEvent{
        private int position;

        private int serviceId;

        private int id;

        public CompleteOrderEvent(int id, int position, int serviceId) {
            this.id = id;
            this.position = position;
            this.serviceId = serviceId;
        }

        public int getId() {
            return id;
        }

        public int getPosition() {
            return position;
        }

        public int getServiceId() {
            return serviceId;
        }
    }

    public static class SendMessageEvent{
        private OrderAPIResponse orderAPIResponse;

        private boolean success;

        public SendMessageEvent(OrderAPIResponse orderAPIResponse, boolean success) {
            this.orderAPIResponse = orderAPIResponse;
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }

        public OrderAPIResponse getOrderAPIResponse() {
            return orderAPIResponse;
        }
    }

    public static class ChangeActivityEvent{

    }

    public static class ChangeTitle{
        private String title;

        public ChangeTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
    public static class ChangeFragmentEvent{

        private Fragment fragment;

        private boolean addToBackStack;

        public ChangeFragmentEvent(Fragment fragment, boolean addToBackStack) {
            this.fragment = fragment;
            this.addToBackStack = addToBackStack;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public boolean isAddToBackStack() {
            return addToBackStack;
        }
    }

    public static class MakePhoneCallEvent{

        private OrderAPIResponse orderAPIResponse;

        public MakePhoneCallEvent(OrderAPIResponse orderAPIResponse) {
            this.orderAPIResponse = orderAPIResponse;
        }

        public OrderAPIResponse getOrderAPIResponse() {
            return orderAPIResponse;
        }
    }
}
