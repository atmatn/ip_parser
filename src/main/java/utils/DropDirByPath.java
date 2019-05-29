package utils;

import java.io.File;

public class DropDirByPath {

    public boolean drop(String path) {

        File file = new File(path);

        if (file.exists()) {

            String[] list = file.list();

            if(list.length==0){

                file.delete();

            }

            for (int i = 0; i < list.length; i++) {

                if(new File(path+"/"+list[i]).isDirectory()){

                    drop(path+"/"+list[i]);

                }else if(new File(path+"/"+list[i]).isFile()){

                    new File(path+"/"+list[i]).delete();

                }

            }

            new File(path).delete();

            return true;
        }
        return false;
    }
}


class TestDropDirByPath {
    public static void main(String[] args) {

        DropDirByPath d = new DropDirByPath();

        boolean drop = d.drop("E:/output");

        System.out.println(drop);

    }
}