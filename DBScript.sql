--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-02-17 17:49:25
CREATE DATABASE cuadratura_hogar WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Chile.1252' LC_CTYPE = 'Spanish_Chile.1252';


ALTER DATABASE cuadratura_hogar OWNER TO admin_hogar;

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
    "DOMAIN_EXTERNAL_ID" character varying(50),
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
    "SERVICE_START" date,
    "PERIODO_FACT" character varying(2)
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
    "KEY_CANAL" character varying(30),
    "PERIODO_FACT" character varying(2)
);


ALTER TABLE public.facturador_kenan_canal OWNER TO admin_hogar;

--
-- TOC entry 230 (class 1259 OID 16509)
-- Name: int_3play_no_kenan_idfact; Type: TABLE; Schema: public; Owner: admin_hogar
--

CREATE TABLE public.int_3play_no_kenan_idfact (
    "RUT" character varying(20),
    "RUT_SIN_DV" character varying(20),
    "CUENTA_SAPRMCA" character varying(20),
    "ID_FACT_KENAN" character varying(50),
    "ESTADO_FAC" character varying(5),
    "DESC_ESTADO_FACT" character varying(50),
    "FECHA_ESTADO_OXT" character varying(20),
    "FECHA_ENVIO_XML" character varying(20),
    "TIPO_TERMINO" character varying(25),
    "CODIGO_SERVICIO" character varying(25),
    "NRO_COMERCIAL" character varying(20),
    "ESTADO_OXC" character varying(5),
    "ID_BANDA_ANCHA" character varying(50),
    "ID_CUENTA_TV" character varying(30)
);


ALTER TABLE public.int_3play_no_kenan_idfact OWNER TO admin_hogar;

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

-- Completed on 2020-02-17 17:49:26

--
-- PostgreSQL database dump complete
--

