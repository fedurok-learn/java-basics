# This 

is simplistic [AWS S3](https://aws.amazon.com/s3/) client for java, wrapper over [com.amazonaws](https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk).

# To 

interact with S3 use simple `S3client` class. It's interface:
- `listBuckets()`
- `listFiles(String bucketName)` - list all files in a bucket
- `upload(String bucketName, String origName, String destName)` - upload `origName` to `bucketName` as `destName`
- `download(String bucketName, String origin, String dest)` - download `origin` from `bucketName` as `dest`
- `delete(String bucketName, String path)` - delete `path` from `bucketName`
- `multiDelete(String bucketName, String[] paths)` - delete multiple `paths` from `bucketName`

# You

can find `S3client` in `/src/main/java/awss3/` and use it in your code. 

# For 

examples see `S3User` in `/src/main/java/demo`, don't forget to set your [access key, secret key](https://medium.com/@shamnad.p.s/how-to-create-an-s3-bucket-and-aws-access-key-id-and-secret-access-key-for-accessing-it-5653b6e54337) 
and bucket name as environment variables in `Edit configuration`. But be careful, don't show it or `.idea` folder to untrusted person. 

Also change your region to [appropriate](https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/regions/Regions.html), depending on [selected for current bucket](https://docs.aws.amazon.com/general/latest/gr/rande.html).
