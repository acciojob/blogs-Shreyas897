package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        Blog blog=blogRepository2.findById(blogId).get();

        List<Image>imageList=blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);
        image.setBlog(blog);
        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id){
        //Image image=imageRepository2.findById(id).get();
        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();
        String dimensions=image.getDimensions();
        String[] screen=screenDimensions.split("x");
        String[] imageSize=dimensions.split("x");
        return((Integer.parseInt(screen[0])/Integer.parseInt(imageSize[0]))*(Integer.parseInt(screen[1])/Integer.parseInt(imageSize[1])));

    }
}
