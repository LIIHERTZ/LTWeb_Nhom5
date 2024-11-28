package FurSPS.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryConfig {
    private static Cloudinary cloudinary;

    static {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dpbczkdlt",     // Thay YOUR_CLOUD_NAME với Cloud name của bạn
                "api_key", "831526976883649",           // Thay YOUR_API_KEY với API Key của bạn
                "api_secret", "QXiDWhJZlRFq2jzVJEiD6ZtjMEc"));   // Thay YOUR_API_SECRET với API Secret của bạn
    }

    public static Cloudinary getCloudinary() {
        return cloudinary;
    }
}