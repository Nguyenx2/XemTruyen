package com.example.xemtruyen.constant;

public class Constant {
    public static class CommonConstants {
        public static final String SUCCESSFULLY = "constant.Constant.CommonConstants.SUCCESS";
        public static final String ENCODING_UTF_8 = "UTF-8";
        public static final String LANGUAGE = "Accept-Language";
        public static final String DEFAULT_LANGUAGE = "en";
    }

    public static class CodeValue {
        public static final Integer SUCCESS = 200;
        public static final Integer CREATED = 201;
        public static final Integer NOT_FOUND = 404;
        public static final Integer CONFLICT = 409;
        public static final Integer BAD_REQUEST = 400;
        public static final Integer UNAUTHORIZED = 403;
        public static final Integer INTERNAL_SERVER_ERROR = 500;
    }

    public static class MessageException {
        // Author
        public static final String CREATE_AUTHOR_SUCCESS = "controllers.AuthorController.create";
        public static final String UPDATE_AUTHOR_SUCCESS = "controllers.AuthorController.update";

        // Genre
        public static final String CREATE_GENRE_SUCCESS = "controllers.GenreController.create";
        public static final String UPDATE_GENRE_SUCCESS = "controllers.GenreController.update";

        // Story
        public static final String CREATE_STORY_SUCCESS = "controllers.StoryController.create";
        public static final String UPDATE_STORY_SUCCESS = "controllers.StoryController.update";

        // Chapter
        public static final String CREATE_CHAPTER_SUCCESS = "controllers.ChapterController.create";
        public static final String UPDATE_CHAPTER_SUCCESS = "controllers.ChapterController.update";

        // Chapter Image
        public static final String CREATE_CHAPTER_IMAGES_SUCCESS = "controllers.ChapterImageController.create";

        // Comment
        public static final String INSERT_COMMENT_SUCCESS = "controllers.CommentController.insert";
        public static final String UPDATE_COMMENT_SUCCESS = "controllers.CommentController.update";

        // User
        public static final String CREATE_USER_SUCCESS = "controllers.UserController.create";
        public static final String UPDATE_USER_SUCCESS = "controllers.UserController.update";
        public static final String CHANGE_PASSWORD_SUCCESS = "controllers.UserController.change_password";
        public static final String ENABLED_USER_SUCCESS = "controllers.UserController.enabled";
        public static final String BLOCKED_USER_SUCCESS = "controllers.UserController.blocked";

        // Authentication
        public static final String LOGIN_SUCCESSFULLY = "controllers.AuthenticationController.login";


    }
}
