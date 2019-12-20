package demo;

import awss3.S3client;
import com.amazonaws.regions.Regions;

import java.io.IOException;

public class S3User {

    // don't forget to set environment variables to it's values
    // it is very sensible data
    private static final String ACCESS_KEY = System.getenv("AWS_S3_ACCESS_KEY");
    private static final String SECRET_KEY = System.getenv("AWS_S3_SECRET_KEY");
    private static final String BUCKET_NAME = System.getenv("AWS_S3_BUCKET_NAME");


    public static void main(String[] args) {
        if (ACCESS_KEY.isEmpty() || SECRET_KEY.isEmpty() || BUCKET_NAME.isEmpty()) {
            System.out.println("Please set up environment variables to it's values!(see code)");
        }
        S3client client = new S3client(
                ACCESS_KEY,
                SECRET_KEY,
                Regions.US_EAST_2 // or whatever region you settled up
        );

        client.listBuckets();
        client.upload(BUCKET_NAME, "img.png", "img.png");

        try {
            client.download(BUCKET_NAME, "img.png", "imgDownloaded.png");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        client.listFiles(BUCKET_NAME);

        client.upload(BUCKET_NAME, "img.png", "img1.png");
        client.listFiles(BUCKET_NAME);
        client.upload(BUCKET_NAME, "img.png", "img2.png");
        client.listFiles(BUCKET_NAME);

        client.delete(BUCKET_NAME, "img2.png");
        client.listFiles(BUCKET_NAME);

        client.multiDelete(BUCKET_NAME, new String[]{"img.png", "img1.png"});
        client.listFiles(BUCKET_NAME);
    }
}
