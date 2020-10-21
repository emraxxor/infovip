--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: seq_cfg_application_meta; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_cfg_application_meta
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_cfg_application_meta OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cfg_application_meta_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cfg_application_meta_data (
    id bigint DEFAULT nextval('public.seq_cfg_application_meta'::regclass) NOT NULL,
    title text,
    meta_keyword text,
    meta_description text,
    og_type text,
    og_title text,
    og_description text,
    thumbnail text,
    meta_url character varying(20),
    is_active boolean DEFAULT false NOT NULL,
    thumbnail_link text
);


ALTER TABLE public.cfg_application_meta_data OWNER TO postgres;

--
-- Name: cfg_log_pid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cfg_log_pid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cfg_log_pid_seq OWNER TO postgres;

--
-- Name: cfg_reg_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cfg_reg_log (
    pid bigint DEFAULT nextval('public.cfg_log_pid_seq'::regclass) NOT NULL,
    ip character varying(39) NOT NULL,
    uid bigint NOT NULL,
    "time" date NOT NULL
);


ALTER TABLE public.cfg_reg_log OWNER TO postgres;

--
-- Name: user_uid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_uid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_uid_seq OWNER TO postgres;

--
-- Name: cfg_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cfg_users (
    uid bigint DEFAULT nextval('public.user_uid_seq'::regclass) NOT NULL,
    uname character varying(255) NOT NULL,
    upassword character varying(255) NOT NULL,
    umail character varying(255) NOT NULL,
    upicture character varying(200),
    is_active boolean NOT NULL,
    city character varying(200),
    country character varying(200),
    firstname character varying(200),
    lastname character varying(200),
    county character varying(200),
    last_seen timestamp without time zone
);


ALTER TABLE public.cfg_users OWNER TO postgres;

--
-- Name: user_blog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_blog (
    bid bigint NOT NULL,
    bname character varying(200) NOT NULL,
    userid bigint NOT NULL,
    "creationTime" timestamp without time zone NOT NULL
);


ALTER TABLE public.user_blog OWNER TO postgres;

--
-- Name: user_blog_bid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_blog_bid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_blog_bid_seq OWNER TO postgres;

--
-- Name: user_blog_bid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_blog_bid_seq OWNED BY public.user_blog.bid;


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: user_blog bid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_blog ALTER COLUMN bid SET DEFAULT nextval('public.user_blog_bid_seq'::regclass);


--
-- Name: cfg_application_meta_data cfg_app_meta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cfg_application_meta_data
    ADD CONSTRAINT cfg_app_meta_pkey PRIMARY KEY (id);


--
-- Name: cfg_reg_log cfg_log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cfg_reg_log
    ADD CONSTRAINT cfg_log_pkey PRIMARY KEY (pid);


--
-- Name: cfg_users cfg_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cfg_users
    ADD CONSTRAINT cfg_users_pkey PRIMARY KEY (uid);


--
-- Name: cfg_users cfg_users_umail_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cfg_users
    ADD CONSTRAINT cfg_users_umail_key UNIQUE (umail);


--
-- Name: cfg_reg_log uk_fh5x9y4wvhdyh5ag31it8e8mc; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cfg_reg_log
    ADD CONSTRAINT uk_fh5x9y4wvhdyh5ag31it8e8mc UNIQUE (uid);


--
-- Name: user_blog user_blog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_blog
    ADD CONSTRAINT user_blog_pkey PRIMARY KEY (bid);


--
-- Name: cfgappmeta_active_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX cfgappmeta_active_idx ON public.cfg_application_meta_data USING btree (is_active);


--
-- Name: cfgappmeta_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX cfgappmeta_idx ON public.cfg_application_meta_data USING btree (id);


--
-- Name: cfgappmeta_type_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX cfgappmeta_type_idx ON public.cfg_application_meta_data USING btree (meta_url);


--
-- Name: nbtree_ctype; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX nbtree_ctype ON public.cfg_application_meta_data USING btree (meta_url varchar_ops);


--
-- Name: cfg_reg_log fg_uid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cfg_reg_log
    ADD CONSTRAINT fg_uid FOREIGN KEY (uid) REFERENCES public.cfg_users(uid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_blog fgx_uid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_blog
    ADD CONSTRAINT fgx_uid FOREIGN KEY (userid) REFERENCES public.cfg_users(uid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

