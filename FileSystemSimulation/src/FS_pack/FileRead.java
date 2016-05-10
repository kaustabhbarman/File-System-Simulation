package FS_pack;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Rushil on 20-Apr-16.
 *
 */

class FileRead {
    private static int DATA_WIDTH = 100, LOOKUP_TABLE_LENGTH = 100, META_DATA_LENGTH = 100, FILE_DATA_LENGTH = 800;

//    List<String> all_files_index() {
//        List<String> comp_list = new ArrayList<>();
//        String lut, temp = "";
//        long temp2 = -1;
//        char ch;
//
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("container.txt"));
//            String foo = br.readLine();
//            int count = 0;
//
//            while (foo != null && count < LOOKUP_TABLE_LENGTH) {
//                if (foo.contains("&")) {
//                    temp = foo.substring(0, 92).replaceAll("$", "");
//                    temp2 = Long.parseLong(foo.substring(92, 100));
//                }
//                while (temp2 != -1){
//                    temp =
//                }
//                foo = br.readLine();
//                count++;
//            }
//            String bar = '%' + conv_string(count) + '|' + conv_string_3(file_name) + '|' + conv_string(index);
//            FileWriterCustom obj = new FileWriterCustom();
//            obj.write_data(bar, 0);
//            br.close();
//
//            for (int i = 0; i < lut.length(); i++) {
//                ch = lut.charAt(i);
//                if (ch == '&') {
//                    temp = lut.substring(i + 1, i + 52).replaceAll("$", "");
//                    temp2 = Integer.parseInt(lut.substring(i + 53, i + 60));
//                    while (temp2 != 0) {
//                        temp = lut.substring(temp2 + 1, temp2 + 52).replaceAll("$", "") + '/' + temp;
//                        temp2 = Integer.parseInt(lut.substring(temp2 + 53, temp2 + 60));
//                    }
//                    i += 60;
//
//                } else if (ch == '%') {
//                    temp = lut.substring(i + 9, i + 52).replaceAll("$", "");
//                    System.out.println(temp);
//                    System.out.println(lut.substring(i + 1, i + 8));
//                    temp2 = Integer.parseInt(lut.substring(i + 1, i + 8).replaceAll("$", ""));
//                    while (temp2 != 0) {
//                        temp = lut.substring(temp2 + 1, temp2 + 52).replaceAll("$", "") + '/' + temp;
//                        temp2 = Integer.parseInt(lut.substring(temp2 + 53, temp2 + 60).replaceAll("$", ""));
//                    }
//                    i += 60;
//                }
//                comp_list.add(temp);
//            }
//            raf.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return comp_list;
//    }

    String file_data(String file_name) {
        String foo, file_data = "";
        int count = 0;
        StorageData file_obj = null;
        boolean marker = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader("container.txt"));
            foo = br.readLine();
            while (foo != null && count < LOOKUP_TABLE_LENGTH) {
                if (foo.contains(file_name)) {
                    FileWriterCustom obj = new FileWriterCustom();
                    file_obj = obj.parser_lut_file(foo);
                    marker = true;
                    break;
                }
                foo = br.readLine();
                count++;
            }
            if (!marker) {
                throw new Error("No such file");
            }
            br.close();
            StorageData sd = find_in_file(file_obj.tail);
            file_data = sd.data;
            while (true) {
                if (sd.tail == 0) {
                    break;
                } else {
                    sd = find_in_file(sd.tail);
                    file_data += sd.data;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file_data;
    }

    private StorageData find_in_file(long index) {
        String foo;
        StorageData sd = null;
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("container.txt"));
            foo = br.readLine();
            while (foo != null) {
                if (i == index) {
                    FileWriterCustom obj = new FileWriterCustom();
                    sd = obj.parser_data(foo);
                    break;

                } else {
                    foo = br.readLine();
                    i++;
                }

            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sd;
    }
}