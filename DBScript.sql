--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2019-12-11 15:26:52

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
-- TOC entry 2952 (class 1262 OID 16394)
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
-- TOC entry 203 (class 1259 OID 16398)
-- Name: canales_3play_62; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.canales_3play_62 (
    "KEY_CANAL" character varying(30),
    "RUT" character varying(15),
    "DV" character varying(2),
    "DETALLE" character varying(10),
    "PRODUCTO" character varying(30),
    "CODI_PRODUCTO" character varying(10),
    "TRYBUY" character varying(4),
    "ESTADO" character varying(30)
);


ALTER TABLE public.canales_3play_62 OWNER TO admin_hogar;

--
-- TOC entry 226 (class 1259 OID 16467)
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
-- TOC entry 204 (class 1259 OID 16401)
-- Name: canales_kaltura; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.canales_kaltura (
    "KEY_CANAL" character varying(30),
    "DOMAIN_EXTERNAL_ID" character varying(15),
    "MODULE_ID" character varying(10),
    "MODULE_NAME" character varying(30),
    "PLAN_DEPORTES" character varying(20),
    "ID_CUENTA_TV_3PLAY" character varying(15)
);


ALTER TABLE public.canales_kaltura OWNER TO admin_hogar;

--
-- TOC entry 205 (class 1259 OID 16404)
-- Name: cod_kal_tplay; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.cod_kal_tplay (
    rut character varying(40),
    codi_kaltura character varying(40),
    codi_tplay character varying(40)
);


ALTER TABLE public.cod_kal_tplay OWNER TO admin_hogar;

--
-- TOC entry 206 (class 1259 OID 16407)
-- Name: cod_serv_kaltura; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.cod_serv_kaltura (
    "RUT_SIN_DV" character varying(40),
    "CODI_SERVICIO" character varying(40)
);


ALTER TABLE public.cod_serv_kaltura OWNER TO admin_hogar;

--
-- TOC entry 207 (class 1259 OID 16410)
-- Name: cod_serv_tplay; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.cod_serv_tplay (
    "RUT_SIN_DV" character varying(40),
    "CODI_SERVICIO" character varying(40)
);


ALTER TABLE public.cod_serv_tplay OWNER TO admin_hogar;

--
-- TOC entry 208 (class 1259 OID 16413)
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
    "SERVICE_START" date
);


ALTER TABLE public.facturador_kenan OWNER TO admin_hogar;

--
-- TOC entry 209 (class 1259 OID 16416)
-- Name: facturador_kenan_62; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.facturador_kenan_62 (
    "RUT_SIN_DV" character varying(15),
    "RUT" character varying(15),
    "NEGOCIO" character varying(20),
    "FECHA_VCTO" date,
    "NRO_FOLIO" character varying(5),
    "SALDO" character varying(15),
    "DIAS_VCTO" character varying(4),
    "OBSERVACION" character varying(45)
);


ALTER TABLE public.facturador_kenan_62 OWNER TO admin_hogar;

--
-- TOC entry 210 (class 1259 OID 16419)
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
    "KEY_CANAL" character varying(30)
);


ALTER TABLE public.facturador_kenan_canal OWNER TO admin_hogar;

--
-- TOC entry 211 (class 1259 OID 16422)
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
-- TOC entry 212 (class 1259 OID 16425)
-- Name: internet_3play_62; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.internet_3play_62 (
    "CODI_TECNICO" character varying(30),
    "NRUT_CLIENTE" character varying(15),
    "DRUT_CLIENTE" character varying(5),
    "DESC_GLOSAPROD" character varying(30),
    "NMRO_SOLICITUDACT" character varying(10)
);


ALTER TABLE public.internet_3play_62 OWNER TO admin_hogar;

--
-- TOC entry 228 (class 1259 OID 16474)
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
-- TOC entry 213 (class 1259 OID 16428)
-- Name: internet_aaa; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.internet_aaa (
    "GROUP" character varying(35),
    "UID" character varying(40)
);


ALTER TABLE public.internet_aaa OWNER TO admin_hogar;

--
-- TOC entry 214 (class 1259 OID 16431)
-- Name: internet_bscs; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.internet_bscs (
    "NMRO_MOVIL" character varying(11),
    "VRUT_CLIENTE" character varying(20),
    "ESTADO" character varying(2),
    "CODI_PLANBSCS" character varying(10),
    "CODI_GRUPO" character varying(10),
    "VCTA_IDCUENTA" character varying(20),
    "CODI_HLR" character varying(10),
    "CODI_ICCID" character varying(30),
    "CODI_IMSI" character varying(30),
    "CODI_MOTIVOESTADO" character varying(5),
    "FECH_ACTIVACION" date,
    "NMRO_CONTRATO" character varying(20),
    "VCTA_SUSCRIPTORA" character varying(20)
);


ALTER TABLE public.internet_bscs OWNER TO admin_hogar;

--
-- TOC entry 215 (class 1259 OID 16434)
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
-- TOC entry 216 (class 1259 OID 16437)
-- Name: tlf_3play_62; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.tlf_3play_62 (
    "CODI_TECNICO" character varying(30),
    "NRUT_CLIENTE" character varying(15),
    "DRUT_CLIENTE" character varying(5),
    "DESC_GLOSAPROD" character varying(30),
    "NMRO_SOLICITUDACT" character varying(10),
    "KEY_ANI" character varying(30)
);


ALTER TABLE public.tlf_3play_62 OWNER TO admin_hogar;

--
-- TOC entry 229 (class 1259 OID 16477)
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
-- TOC entry 217 (class 1259 OID 16440)
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
-- TOC entry 218 (class 1259 OID 16443)
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
-- TOC entry 219 (class 1259 OID 16446)
-- Name: tv_3play_62; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.tv_3play_62 (
    "KEY_CANAL" character varying(30),
    "RUT" character varying(15),
    "DV" character varying(2),
    "DETALLE" character varying(10),
    "PRODUCTO" character varying(30),
    "CODI_PRODUCTO" character varying(10),
    "TRYBUY" character varying(4),
    "ESTADO" character varying(30)
);


ALTER TABLE public.tv_3play_62 OWNER TO admin_hogar;

--
-- TOC entry 227 (class 1259 OID 16470)
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
-- TOC entry 220 (class 1259 OID 16449)
-- Name: tv_bscs; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.tv_bscs (
    "NMRO_MOVIL" character varying(11),
    "VRUT_CLIENTE" character varying(20),
    "ESTADO" character varying(2),
    "CODI_PLANBSCS" character varying(10),
    "CODI_GRUPO" character varying(10),
    "VCTA_IDCUENTA" character varying(20),
    "CODI_HLR" character varying(10),
    "CODI_ICCID" character varying(30),
    "CODI_IMSI" character varying(30),
    "CODI_MOTIVOESTADO" character varying(5),
    "FECH_ACTIVACION" date,
    "NMRO_CONTRATO" character varying(20),
    "VCTA_SUSCRIPTORA" character varying(20)
);


ALTER TABLE public.tv_bscs OWNER TO admin_hogar;

--
-- TOC entry 221 (class 1259 OID 16452)
-- Name: tv_intraway; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.tv_intraway (
    "PAIS" character varying(32),
    "IDEMPRESA" character varying(10),
    "EMPRESA" character varying(10),
    "IDCLIENTECRM" character varying(20),
    "NOMBRE" character varying(20),
    "FECHAALTA" date,
    "FECHAACTIVACION" date,
    "SMARTCARDID" character varying(20),
    "STBID" character varying(20),
    "DISABLED" character varying(5),
    "CASID" character varying(20),
    "CRMID" character varying(50),
    "TIPO" character varying(20),
    "NAME" character varying(50),
    "NAVSTBID" character varying(20),
    "IDPRODUCTO" character varying(20),
    "IDVENTA" character varying(30),
    "IDSERVICIO" character varying(5),
    "IDPRODUCTOPADRE" character varying(30),
    "IDVENTAPADRE" character varying(5),
    "IDSERVICIOPADRE" character varying(5),
    "IDCLIENTE" character varying(20),
    "ZONA" character varying(5),
    "ZIPCODE" character varying(10),
    "STBTYPE" character varying(30)
);


ALTER TABLE public.tv_intraway OWNER TO admin_hogar;

--
-- TOC entry 222 (class 1259 OID 16455)
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
-- TOC entry 223 (class 1259 OID 16458)
-- Name: tv_vmica_bscs; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.tv_vmica_bscs (
    "RUT" character varying(20),
    "ID_INTRAWAY" character varying(20)
);


ALTER TABLE public.tv_vmica_bscs OWNER TO admin_hogar;

--
-- TOC entry 224 (class 1259 OID 16461)
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
-- TOC entry 225 (class 1259 OID 16464)
-- Name: voz_bscs; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.voz_bscs (
    "NMRO_MOVIL" character varying(11),
    "VRUT_CLIENTE" character varying(20),
    "ESTADO" character varying(2),
    "CODI_PLANBSCS" character varying(10),
    "CODI_GRUPO" character varying(10),
    "VCTA_IDCUENTA" character varying(20),
    "CODI_HLR" character varying(10),
    "CODI_ICCID" character varying(30),
    "CODI_IMSI" character varying(30),
    "CODI_MOTIVOESTADO" character varying(5),
    "FECH_ACTIVACION" date,
    "NMRO_CONTRATO" character varying(20),
    "VCTA_SUSCRIPTORA" character varying(20)
);


ALTER TABLE public.voz_bscs OWNER TO admin_hogar;

--
-- TOC entry 2919 (class 0 OID 16395)
-- Dependencies: 202
-- Data for Name: bdservicios_retirados; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2920 (class 0 OID 16398)
-- Dependencies: 203
-- Data for Name: canales_3play_62; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2943 (class 0 OID 16467)
-- Dependencies: 226
-- Data for Name: canales_3play_red; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2921 (class 0 OID 16401)
-- Dependencies: 204
-- Data for Name: canales_kaltura; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2922 (class 0 OID 16404)
-- Dependencies: 205
-- Data for Name: cod_kal_tplay; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2923 (class 0 OID 16407)
-- Dependencies: 206
-- Data for Name: cod_serv_kaltura; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2924 (class 0 OID 16410)
-- Dependencies: 207
-- Data for Name: cod_serv_tplay; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2925 (class 0 OID 16413)
-- Dependencies: 208
-- Data for Name: facturador_kenan; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2926 (class 0 OID 16416)
-- Dependencies: 209
-- Data for Name: facturador_kenan_62; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2927 (class 0 OID 16419)
-- Dependencies: 210
-- Data for Name: facturador_kenan_canal; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2928 (class 0 OID 16422)
-- Dependencies: 211
-- Data for Name: internet_3play; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2929 (class 0 OID 16425)
-- Dependencies: 212
-- Data for Name: internet_3play_62; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2945 (class 0 OID 16474)
-- Dependencies: 228
-- Data for Name: internet_3play_red; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2930 (class 0 OID 16428)
-- Dependencies: 213
-- Data for Name: internet_aaa; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2931 (class 0 OID 16431)
-- Dependencies: 214
-- Data for Name: internet_bscs; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2932 (class 0 OID 16434)
-- Dependencies: 215
-- Data for Name: tlf_3play; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2933 (class 0 OID 16437)
-- Dependencies: 216
-- Data for Name: tlf_3play_62; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2946 (class 0 OID 16477)
-- Dependencies: 229
-- Data for Name: tlf_3play_red; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2934 (class 0 OID 16440)
-- Dependencies: 217
-- Data for Name: tlf_otcar; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2935 (class 0 OID 16443)
-- Dependencies: 218
-- Data for Name: todo_kaltura; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2936 (class 0 OID 16446)
-- Dependencies: 219
-- Data for Name: tv_3play_62; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2944 (class 0 OID 16470)
-- Dependencies: 227
-- Data for Name: tv_3play_red; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2937 (class 0 OID 16449)
-- Dependencies: 220
-- Data for Name: tv_bscs; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2938 (class 0 OID 16452)
-- Dependencies: 221
-- Data for Name: tv_intraway; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2939 (class 0 OID 16455)
-- Dependencies: 222
-- Data for Name: tv_kaltura; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2940 (class 0 OID 16458)
-- Dependencies: 223
-- Data for Name: tv_vmica_bscs; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2941 (class 0 OID 16461)
-- Dependencies: 224
-- Data for Name: tvcanales_3play; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



--
-- TOC entry 2942 (class 0 OID 16464)
-- Dependencies: 225
-- Data for Name: voz_bscs; Type: TABLE DATA; Schema: public; Owner: admin_hogar
--



-- Completed on 2019-12-11 15:26:52

--
-- PostgreSQL database dump complete
--

