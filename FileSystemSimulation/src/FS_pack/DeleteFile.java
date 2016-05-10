package FS_pack;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Rushil on 22-Apr-16.
 */
public class DeleteFile {
    private static int DATA_WIDTH = 100, LOOKUP_TABLE_LENGTH = 100, META_DATA_LENGTH = 100, FILE_DATA_LENGTH = 800;

    void del_file(String file_name) {
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
            FileWriterCustom obj = new FileWriterCustom();
            obj.write_data(obj.none_value, count);
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
            FileWriterCustom obj = new FileWriterCustom();
            obj.write_data(obj.none_value, i);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sd;
    }
}
