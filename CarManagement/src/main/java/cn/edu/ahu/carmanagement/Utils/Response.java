package cn.edu.ahu.carmanagement.Utils;

/**
 * 全局前台回应封装
 * Created by Administrator on 2017/4/21.
 */

public class Response {
    private static final String OK = "ok";
    private static final String ERROR = "error";
    private Response.Meta meta;
    private Object data;

    public Response() {
    }

    public Response success() {
        this.meta = new Response.Meta(true, "ok");
        return this;
    }

    public Response success(Object data) {
        this.meta = new Response.Meta(true, "ok");
        this.data = data;
        return this;
    }

    public Response failure() {
        this.meta = new Response.Meta(false, "error");
        return this;
    }

    public Response failure(String message) {
        this.meta = new Response.Meta(false, message);
        return this;
    }

    public Response.Meta getMeta() {
        return this.meta;
    }

    public void setMeta(Response.Meta meta) {
        this.meta = meta;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static class Meta {
        private boolean success;
        private String message;

        public Meta() {
        }

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean getSuccess() {
            return this.success;
        }
    }
}

