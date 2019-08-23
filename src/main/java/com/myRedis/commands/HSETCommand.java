package com.myRedis.commands;

import com.myRedis.Command;
import com.myRedis.Database;
import com.myRedis.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class HSETCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(HSETCommand.class);
    private List<Object> args;
    @Override
    public void setArgs(List<Object> args) {
        this.args = args;
    }

    @Override
    public void run(OutputStream os) throws IOException {
        String key = new String((byte[]) args.get(0));
        String field = new String((byte[])args.get(1));
        String value = new String((byte[])args.get(2));
        logger.debug("运行的是 hset 命令: {} {} {}", key, field,value);
        Map<String,String> hash = Database.getHash(key);
        boolean isUpdate = hash.containsKey(field);
        hash.put(field,value);
        logger.debug("插入后数据共有 {} 个", hash.size());
        if (isUpdate){
            Protocol.writeInteger(os,0);
        }else {
            Protocol.writeInteger(os,1);
        }
    }
}
