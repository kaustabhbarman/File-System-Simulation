package FS_pack;

/**
 * Created by Rushil on 20-Apr-16.
 *
 *
 *
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileSysSim {

    // Constructor
    private FileSysSim() {
        File f = new File("container.txt");
        boolean bool = f.exists();

        if (!bool) {
            Container cc = new Container();
            cc.create();
        }

    }

    public static void main(String[] args) {
        FileSysSim sim = new FileSysSim();
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1 - Edit file");
            System.out.println("2 - create_folder");
            System.out.println("3 - create_file");
            System.out.println("4 - read_file");
            System.out.println("5 - View all file + dir structure");
            System.out.println("6 - Delete File");
            System.out.println("7 - Exit");
            System.out.println("-------------------------------------");
            int i = in.nextInt();

            switch (i) {
                case 1:
                    System.out.println("Not working.. Yet");
                    break;
                case 2:
                    create_folder();
                    break;
                case 3:
                    create_file_wrapper();
                    break;
                case 4:
                    read_file();
                    break;
                case 5:
                    all_files();
                    break;
                case 6:
                    del_file();
                    break;
                case 7:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Invalid");

            }
        }
    }

    private static void create_file_wrapper() {
        Scanner in = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Enter parent folder name");
        String dir = in.nextLine();
        System.out.println("Enter file name to be created");
        String file = in.nextLine();
        System.out.println("Enter file data");
        String data = in.nextLine();
        create_file(data, dir, file);
        System.out.println("File created");
    }

    private static void create_file(String data, String dir_name, String file_name) {
        FileCreate file = new FileCreate(data);
        long index = file.store_file();

        LUT lut_entry = new LUT();
        lut_entry.create_file(index, dir_name, file_name);
    }

    private static void create_folder() {
        Scanner in = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Enter parent folder name");
        String parent = in.nextLine();
        System.out.println("Enter folder name to be created");
        String dir = in.nextLine();
        LUT obj = new LUT();
        obj.create_folder(dir, parent);
        System.out.println("Folder created");
    }

    private static void del_file() {
        Scanner in = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Enter file name");
        String file_name = in.nextLine();
        DeleteFile obj = new DeleteFile();
        obj.del_file(file_name);
        System.out.println("File deleted");
    }

    private static void read_file() {
        FileRead obj = new FileRead();
        Scanner in = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Enter file name");
        String file_name = in.nextLine();
        String file_data_s = obj.file_data(file_name);
        System.out.println(file_data_s);
    }

    private static void all_files() {
        FileRead obj = new FileRead();
//        System.out.println(obj.all_files_index());
    }

    // Container Class
    private class Container {
        private void create() {
            System.out.println("Creating Container");
            String tw = "";
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("container.txt"));
                for (int bytes = 0; bytes < 100; bytes++) {
                    tw += "$";
                }
                for (int bytes = 0; bytes < 1000; bytes++) {
                    bw.write(tw);
                    bw.newLine();
                }

                System.out.println("Container Created");
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            LUT obj = new LUT();
            obj.create_root();
            System.out.println("Done\n\n");
        }

    }
}
