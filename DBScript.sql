--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-02-21 23:03:48

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

DROP DATABASE cuadratura_hogar;
--
-- TOC entry 2922 (class 1262 OID 16394)
-- Name: cuadratura_hogar; Type: DATABASE; Schema: -; Owner: admin_hogar
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
-- TOC entry 202 (class 1259 OID 16395)
-- Name: bdservicios_retirados; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 220 (class 1259 OID 25325)
-- Name: canales_3play_nored; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 213 (class 1259 OID 16467)
-- Name: canales_3play_red; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 203 (class 1259 OID 16401)
-- Name: canales_kaltura; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 219 (class 1259 OID 25322)
-- Name: canales_kaltura_no3play; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 204 (class 1259 OID 16413)
-- Name: facturador_kenan; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 205 (class 1259 OID 16419)
-- Name: facturador_kenan_canal; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 206 (class 1259 OID 16422)
-- Name: internet_3play; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 221 (class 1259 OID 25328)
-- Name: internet_3play_nored; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 215 (class 1259 OID 16474)
-- Name: internet_3play_red; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 207 (class 1259 OID 16428)
-- Name: internet_aaa; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.internet_aaa (
    "GROUP" character varying(35),
    "UID" character varying(40)
);


ALTER TABLE public.internet_aaa OWNER TO admin_hogar;

--
-- TOC entry 208 (class 1259 OID 16434)
-- Name: tlf_3play; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 222 (class 1259 OID 25331)
-- Name: tlf_3play_nored; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 216 (class 1259 OID 16477)
-- Name: tlf_3play_red; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 209 (class 1259 OID 16440)
-- Name: tlf_otcar; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 217 (class 1259 OID 25316)
-- Name: tlf_otcar_no3play; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 210 (class 1259 OID 16443)
-- Name: todo_kaltura; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 223 (class 1259 OID 25334)
-- Name: tv_3play_nored; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 214 (class 1259 OID 16470)
-- Name: tv_3play_red; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 211 (class 1259 OID 16455)
-- Name: tv_kaltura; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 218 (class 1259 OID 25319)
-- Name: tv_kaltura_no3play; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 212 (class 1259 OID 16461)
-- Name: tvcanales_3play; Type: TABLE; Schema: public; Owner: admin_hogar
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
-- TOC entry 2867 (class 0 OID 16395)
-- Dependencies: 202
-- Data for Name: bdservicios_retirados; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.bdservicios_retirados ("RUT", "COD_SERVICIO_TECNICO", "ATRIBUTO_NOM", "VALOR_ATRIBUTO", "VALOR_ATRIBUTO_1") FROM stdin;
\.


--
-- TOC entry 2878 (class 0 OID 16467)
-- Dependencies: 213
-- Data for Name: canales_3play_red; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.canales_3play_red ("KEY_CANAL", "RUT", "DV", "DETALLE", "PRODUCTO", "CODI_PRODUCTO", "TRYBUY", "ESTADO") FROM stdin;
\.


--
-- TOC entry 2868 (class 0 OID 16401)
-- Dependencies: 203
-- Data for Name: canales_kaltura; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.canales_kaltura ("KEY_CANAL", "DOMAIN_EXTERNAL_ID", "MODULE_ID", "MODULE_NAME", "PLAN_DEPORTES", "ID_CUENTA_TV_3PLAY") FROM stdin;
\.


--
-- TOC entry 2869 (class 0 OID 16413)
-- Dependencies: 204
-- Data for Name: facturador_kenan; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.facturador_kenan ("KEY_RUT_SIN_DV", "CODIGO_PLAN", "CUENTA_KENAN", "ESTADO", "PLAN", "RUT_CLIENTE", "SERVICE_END", "SERVICE_START", "PERIODO_FACT") FROM stdin;
\.


--
-- TOC entry 2870 (class 0 OID 16419)
-- Dependencies: 205
-- Data for Name: facturador_kenan_canal; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.facturador_kenan_canal ("CANAL", "CODIGO_TPLAY", "CODIGO_CANAL", "CUENTA_KENAN", "ESTADO", "RUT_CLIENTE", "SERVICE_END", "SERVICE_START", "KEY_CANAL", "PERIODO_FACT") FROM stdin;
\.


--
-- TOC entry 2871 (class 0 OID 16422)
-- Dependencies: 206
-- Data for Name: internet_3play; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.internet_3play ("CODI_TECNICO", "NRUT_CLIENTE", "DRUT_CLIENTE", "DESC_GLOSAPROD", "NMRO_SOLICITUDACT") FROM stdin;
\.


--
-- TOC entry 2880 (class 0 OID 16474)
-- Dependencies: 215
-- Data for Name: internet_3play_red; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.internet_3play_red ("CODI_TECNICO", "NRUT_CLIENTE", "DRUT_CLIENTE", "DESC_GLOSAPROD", "NMRO_SOLICITUDACT") FROM stdin;
\.


--
-- TOC entry 2872 (class 0 OID 16428)
-- Dependencies: 207
-- Data for Name: internet_aaa; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.internet_aaa ("GROUP", "UID") FROM stdin;
\.


--
-- TOC entry 2873 (class 0 OID 16434)
-- Dependencies: 208
-- Data for Name: tlf_3play; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.tlf_3play ("CODI_TECNICO", "NRUT_CLIENTE", "DRUT_CLIENTE", "DESC_GLOSAPROD", "NMRO_SOLICITUDACT", "KEY_ANI") FROM stdin;
\.


--
-- TOC entry 2881 (class 0 OID 16477)
-- Dependencies: 216
-- Data for Name: tlf_3play_red; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.tlf_3play_red ("CODI_TECNICO", "NRUT_CLIENTE", "DRUT_CLIENTE", "DESC_GLOSAPROD", "NMRO_SOLICITUDACT", "KEY_ANI") FROM stdin;
\.


--
-- TOC entry 2874 (class 0 OID 16440)
-- Dependencies: 209
-- Data for Name: tlf_otcar; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.tlf_otcar ("KEY_ANI", "REQUEST_ID", "RUT_CLIENT", "DV_CLIENT", "ANI") FROM stdin;
\.


--
-- TOC entry 2875 (class 0 OID 16443)
-- Dependencies: 210
-- Data for Name: todo_kaltura; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.todo_kaltura ("KEY_CANAL", "RUT", "MODULE_ID", "MODULE_NAME", "PLAN_DEPORTES", "HOUSE_HOLD_ID") FROM stdin;
\.


--
-- TOC entry 2879 (class 0 OID 16470)
-- Dependencies: 214
-- Data for Name: tv_3play_red; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.tv_3play_red ("KEY_CANAL", "RUT", "DV", "DETALLE", "PRODUCTO", "CODI_PRODUCTO", "TRYBUY", "ESTADO") FROM stdin;
\.


--
-- TOC entry 2876 (class 0 OID 16455)
-- Dependencies: 211
-- Data for Name: tv_kaltura; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.tv_kaltura ("KEY_RUT_SIN_DV", "DOMAIN_EXTERNAL_ID", "MODULE_ID", "MODULE_NAME", "VALIDA_RUT", "DOMAIN_ID", "SUSPENTION_STATE") FROM stdin;
\.


--
-- TOC entry 2877 (class 0 OID 16461)
-- Dependencies: 212
-- Data for Name: tvcanales_3play; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--

COPY public.tvcanales_3play ("KEY_CANAL", "RUT", "DV", "DETALLE", "PRODUCTO", "CODI_PRODUCTO", "TRYBUY", "ESTADO") FROM stdin;
\.


-- Completed on 2020-02-17 18:35:53

--
-- PostgreSQL database dump complete
--

