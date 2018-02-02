package com.whgtf.sportsbook.common;

import com.whgtf.sportsbook.model.ResponseEvent;
import com.whgtf.sportsbook.restApi.MessageError;
import com.whgtf.sportsbook.utils.SettleItems;

import java.util.HashMap;

/**
 * Created by Juri on 11/11/2016.
 */
public class SettleMain {

    public static void main (String[] args) {
        SettleItems SettleItems = new SettleItems();
        String env = getVMargs("env");
        try {
            SettleItems.settle(env);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ResponseEvent settleEvent (HashMap<String, String> settingsList, MessageError messageError) {
        SettleItems SettleItems = new SettleItems();
        try {
            return SettleItems.settle(settingsList.get("env"));
        } catch (InterruptedException e) {
            messageError.setMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            messageError.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            messageError.setMessage(e.getMessage());
        }
        return null;
    }

    public static String getVMargs (String argumment) {
        String variable = "";
        try {
            variable = String.valueOf(System.getProperty(argumment));
        } catch (NullPointerException e) {
            return variable;
        }
        return variable;
    }
}
