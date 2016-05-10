package FS_pack;

/**
 * Created by Rushil on 20-Apr-16.
 * format - folder - &folder_name|00head00
 * - file - %00head_folder00|file_name|first_chunk_addr
 */

import java.io.BufferedReader;
import java.io.FileReader;

class LUT {
    private static int DATA_WIDTH = 100, LOOKUP_TABLE_LENGTH = 100, META_DATA_LENGTH = 100, FILE_DATA_LENGTH = 800;
    private String none_value, line_reader;

    LUT() {
        String none_value = "";
        for (int i = 0; i <= 100; i++) {
            none_value += '$';
        }
        this.none_value = none_value;
    }

    void create_root() {
        System.out.println("Creating 'root' directory");
        FileWriterCustom obj = new FileWriterCustom();
        String foo = '&' + conv_string_2("root") + "|0000000";
        obj.write_data(foo, 0);

    }

    void create_file(long index, String dir_name, String file_name) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("container.txt"));
            String foo = br.readLine();
            int count = 0, pos = 0;

            while (foo != null && count < LOOKUP_TABLE_LENGTH) {
                if (foo.contains(dir_name)) {
                    pos = count;
                    break;
                }
                foo = br.readLine();
                count++;
            }
            br.close();
            String bar = '%' + conv_string(pos) + '|' + conv_string_3(file_name) + '|' + conv_string(index);
            FileWriterCustom obj = new FileWriterCustom();
            obj.write_data(bar, 0);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void create_folder(String folder, String parent_folder) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("container.txt"));
            String foo = br.readLine();
            int count = 0;

            while (foo != null && count < LOOKUP_TABLE_LENGTH) {
                if (foo.contains(parent_folder)) {
                    break;
                }
                foo = br.readLine();
                count++;
            }
            br.close();
            String bar = '&' + conv_string_2(folder) + '|' + conv_string(count);
            FileWriterCustom obj = new FileWriterCustom();
            obj.write_data(bar, 0);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String conv_string(long a) {
        String bb = Long.toString(a);
        while (bb.length() < 7) {
            bb = '0' + bb;
        }
        return bb;
    }

    private String conv_string_2(String bb) {
        while (bb.length() < 91) {
            bb = '$' + bb;
        }
        return bb;
    }

    private String conv_string_3(String bb) {
        while (bb.length() < 83) {
            bb = '$' + bb;
        }
        return bb;
    }

}
