package com.myRedis.commands;

import com.myRedis.Command;
import com.myRedis.Database;
import com.myRedis.Protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class LRANGECommand implements Command {
    private List<Object> args;
    @Override
    public void setArgs(List<Object> args) {
        this.args = args;
    }

    @Override
    public void run(OutputStream os) throws IOException {
        String key = new String((byte[])args.get(0));
        int start = Integer.parseInt(new String((byte[])args.get(1)));
        int end = Integer.parseInt(new String((byte[])args.get(2)));

        List<String> list = Database.getList(key);
        if (end < 0){
            end = list.size() + end;
        }
        List<String> result = list.subList(start,end+1);
        try {
            Protocol.writeArray(os,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
