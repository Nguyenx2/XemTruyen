package com.example.xemtruyen.constant;

public class Constant {
    public static class CommonConstants {
        public static final String SUCCESS = "constant.Constant.CommonConstants.SUCCESS";
    }

    public static class CodeException {
        public static final Integer NOT_FOUND = 404;
        public static final Integer CONFLICT = 409;
        public static final Integer BAD_REQUEST = 400;
        public static final Integer INTERNAL_SERVER_ERROR = 500;
    }

    public static class MessageException {
        // Author
        public static final String CREATE_AUTHOR_SUCCESS = "controllers.AuthorController.create";
        public static final String UPDATE_AUTHOR_SUCCESS = "controllers.AuthorController.update";

        // Genre
        public static final String CREATE_GENRE_SUCCESS = "controllers.GenreController.create";
        public static final String UPDATE_GENRE_SUCCESS = "controller.GenreController.update";

        // Story
        public static final String CREATE_STORY_SUCCESS = "controller.StoryController.create";
        public static final String UPDATE_STORY_SUCCESS = "controller.StoryController.update";

        // Chapter
        public static final String CREATE_CHAPTER_SUCCESS = "controllers.ChapterController.create";
        public static final String UPDATE_CHAPTER_SUCCESS = "controllers.ChapterController.create";

        // Chapter Image
        public static final String CREATE_CHAPTER_IMAGES_SUCCESS = "controllers.ChapterImageController.create";
        public static final String UPDATE_CHAPTER_IMAGE_SUCCESS = "controllers.ChapterImageController.update";
    }
}
