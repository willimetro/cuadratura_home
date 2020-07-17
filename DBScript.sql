--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

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
-- Name: cuadratura_hogar; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE cuadratura_hogar WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Chile.1252' LC_CTYPE = 'Spanish_Chile.1252';


ALTER DATABASE cuadratura_hogar OWNER TO admin_hogar;

\connect cuadratura_hogar

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bdservicios_retirados; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.bdservicios_retirados (
    "RUT" character varying(15),
    "COD_SERVICIO_TECNICO" character varying(30),
    "ATRIBUTO_NOM" character varying(35),
    "VALOR_ATRIBUTO" character varying(40),
    "VALOR_ATRIBUTO_1" character varying(15)
);


ALTER TABLE public.bdservicios_retirados OWNER TO admin_hogar;

--
-- Name: canales_3play_nored; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.canales_3play_nored (
    "KEY_CANAL" character varying(30),
    "RUT" character varying(15),
    "DV" character varying(2),
    "DETALLE" character varying(10),
    "PRODUCTO" character varying(30),
    "CODI_PRODUCTO" character varying(10),
    "TRYBUY" character varying(4),
    "ESTADO" character varying(30)
);


ALTER TABLE public.canales_3play_nored OWNER TO admin_hogar;

--
-- Name: canales_3play_red; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.canales_3play_red (
    "KEY_CANAL" character varying(30),
    "RUT" character varying(15),
    "DV" character varying(2),
    "DETALLE" character varying(10),
    "PRODUCTO" character varying(30),
    "CODI_PRODUCTO" character varying(10),
    "TRYBUY" character varying(4),
    "ESTADO" character varying(30)
);


ALTER TABLE public.canales_3play_red OWNER TO admin_hogar;

--
-- Name: canales_kaltura; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.canales_kaltura (
    "KEY_CANAL" character varying(30),
    "DOMAIN_EXTERNAL_ID" character varying(50),
    "MODULE_ID" character varying(10),
    "MODULE_NAME" character varying(30),
    "PLAN_DEPORTES" character varying(20),
    "ID_CUENTA_TV_3PLAY" character varying(15)
);


ALTER TABLE public.canales_kaltura OWNER TO admin_hogar;

--
-- Name: canales_kaltura_no3play; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.canales_kaltura_no3play (
    "KEY_CANAL" character varying(30),
    "DOMAIN_EXTERNAL_ID" character varying(50),
    "MODULE_ID" character varying(10),
    "MODULE_NAME" character varying(30),
    "PLAN_DEPORTES" character varying(20),
    "ID_CUENTA_TV_3PLAY" character varying(15)
);


ALTER TABLE public.canales_kaltura_no3play OWNER TO admin_hogar;

--
-- Name: facturador_kenan; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.facturador_kenan (
    "KEY_RUT_SIN_DV" character varying(15),
    "CODIGO_PLAN" character varying(10),
    "CUENTA_KENAN" character varying(15),
    "ESTADO" character varying(20),
    "PLAN" character varying(30),
    "RUT_CLIENTE" character varying(15),
    "SERVICE_END" date,
    "SERVICE_START" date,
    "PERIODO_FACT" character varying(2)
);


ALTER TABLE public.facturador_kenan OWNER TO admin_hogar;

--
-- Name: facturador_kenan_canal; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.facturador_kenan_canal (
    "CANAL" character varying(30),
    "CODIGO_TPLAY" character varying(10),
    "CODIGO_CANAL" character varying(10),
    "CUENTA_KENAN" character varying(15),
    "ESTADO" character varying(15),
    "RUT_CLIENTE" character varying(15),
    "SERVICE_END" date,
    "SERVICE_START" date,
    "KEY_CANAL" character varying(30),
    "PERIODO_FACT" character varying(2)
);


ALTER TABLE public.facturador_kenan_canal OWNER TO admin_hogar;

--
-- Name: internet_3play; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.internet_3play (
    "CODI_TECNICO" character varying(30),
    "NRUT_CLIENTE" character varying(15),
    "DRUT_CLIENTE" character varying(5),
    "DESC_GLOSAPROD" character varying(30),
    "NMRO_SOLICITUDACT" character varying(10)
);


ALTER TABLE public.internet_3play OWNER TO admin_hogar;

--
-- Name: internet_3play_nored; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.internet_3play_nored (
    "CODI_TECNICO" character varying(30),
    "NRUT_CLIENTE" character varying(15),
    "DRUT_CLIENTE" character varying(5),
    "DESC_GLOSAPROD" character varying(30),
    "NMRO_SOLICITUDACT" character varying(10)
);


ALTER TABLE public.internet_3play_nored OWNER TO admin_hogar;

--
-- Name: internet_3play_red; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.internet_3play_red (
    "CODI_TECNICO" character varying(30),
    "NRUT_CLIENTE" character varying(15),
    "DRUT_CLIENTE" character varying(5),
    "DESC_GLOSAPROD" character varying(30),
    "NMRO_SOLICITUDACT" character varying(10)
);


ALTER TABLE public.internet_3play_red OWNER TO admin_hogar;

--
-- Name: internet_aaa; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.internet_aaa (
    "GROUP" character varying(35),
    "UID" character varying(40)
);


ALTER TABLE public.internet_aaa OWNER TO admin_hogar;

--
-- Name: tlf_3play; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tlf_3play (
    "CODI_TECNICO" character varying(30),
    "NRUT_CLIENTE" character varying(15),
    "DRUT_CLIENTE" character varying(5),
    "DESC_GLOSAPROD" character varying(30),
    "NMRO_SOLICITUDACT" character varying(10),
    "KEY_ANI" character varying(30)
);


ALTER TABLE public.tlf_3play OWNER TO admin_hogar;

--
-- Name: tlf_3play_nored; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tlf_3play_nored (
    "CODI_TECNICO" character varying(30),
    "NRUT_CLIENTE" character varying(15),
    "DRUT_CLIENTE" character varying(5),
    "DESC_GLOSAPROD" character varying(30),
    "NMRO_SOLICITUDACT" character varying(10),
    "KEY_ANI" character varying(30)
);


ALTER TABLE public.tlf_3play_nored OWNER TO admin_hogar;

--
-- Name: tlf_3play_red; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tlf_3play_red (
    "CODI_TECNICO" character varying(30),
    "NRUT_CLIENTE" character varying(15),
    "DRUT_CLIENTE" character varying(5),
    "DESC_GLOSAPROD" character varying(30),
    "NMRO_SOLICITUDACT" character varying(10),
    "KEY_ANI" character varying(30)
);


ALTER TABLE public.tlf_3play_red OWNER TO admin_hogar;

--
-- Name: tlf_otcar; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tlf_otcar (
    "KEY_ANI" character varying(30),
    "REQUEST_ID" character varying(20),
    "RUT_CLIENT" character varying(15),
    "DV_CLIENT" character varying(4),
    "ANI" character varying(15)
);


ALTER TABLE public.tlf_otcar OWNER TO admin_hogar;

--
-- Name: tlf_otcar_no3play; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tlf_otcar_no3play (
    "KEY_ANI" character varying(30),
    "REQUEST_ID" character varying(20),
    "RUT_CLIENT" character varying(15),
    "DV_CLIENT" character varying(4),
    "ANI" character varying(15)
);


ALTER TABLE public.tlf_otcar_no3play OWNER TO admin_hogar;

--
-- Name: todo_kaltura; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.todo_kaltura (
    "KEY_CANAL" character varying(35),
    "RUT" character varying(30),
    "MODULE_ID" character varying(20),
    "MODULE_NAME" character varying(40),
    "PLAN_DEPORTES" character varying(40),
    "HOUSE_HOLD_ID" character varying(40)
);


ALTER TABLE public.todo_kaltura OWNER TO admin_hogar;

--
-- Name: tv_3play_nored; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tv_3play_nored (
    "KEY_CANAL" character varying(30),
    "RUT" character varying(15),
    "DV" character varying(2),
    "DETALLE" character varying(10),
    "PRODUCTO" character varying(30),
    "CODI_PRODUCTO" character varying(10),
    "TRYBUY" character varying(4),
    "ESTADO" character varying(30)
);


ALTER TABLE public.tv_3play_nored OWNER TO admin_hogar;

--
-- Name: tv_3play_red; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tv_3play_red (
    "KEY_CANAL" character varying(30),
    "RUT" character varying(15),
    "DV" character varying(2),
    "DETALLE" character varying(10),
    "PRODUCTO" character varying(30),
    "CODI_PRODUCTO" character varying(10),
    "TRYBUY" character varying(4),
    "ESTADO" character varying(30)
);


ALTER TABLE public.tv_3play_red OWNER TO admin_hogar;

--
-- Name: tv_kaltura; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tv_kaltura (
    "KEY_RUT_SIN_DV" character varying(30),
    "DOMAIN_EXTERNAL_ID" character varying(30),
    "MODULE_ID" character varying(10),
    "MODULE_NAME" character varying(30),
    "VALIDA_RUT" character varying(15),
    "DOMAIN_ID" character varying(15),
    "SUSPENTION_STATE" character varying(20)
);


ALTER TABLE public.tv_kaltura OWNER TO admin_hogar;

--
-- Name: tv_kaltura_no3play; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tv_kaltura_no3play (
    "KEY_RUT_SIN_DV" character varying(30),
    "DOMAIN_EXTERNAL_ID" character varying(30),
    "MODULE_ID" character varying(10),
    "MODULE_NAME" character varying(30),
    "VALIDA_RUT" character varying(15),
    "DOMAIN_ID" character varying(15),
    "SUSPENTION_STATE" character varying(20)
);


ALTER TABLE public.tv_kaltura_no3play OWNER TO admin_hogar;

--
-- Name: tvcanales_3play; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tvcanales_3play (
    "KEY_CANAL" character varying(30),
    "RUT" character varying(15),
    "DV" character varying(2),
    "DETALLE" character varying(10),
    "PRODUCTO" character varying(30),
    "CODI_PRODUCTO" character varying(10),
    "TRYBUY" character varying(4),
    "ESTADO" character varying(30)
);


ALTER TABLE public.tvcanales_3play OWNER TO admin_hogar;

--
-- PostgreSQL database dump complete
--

