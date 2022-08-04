/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.network.models.base.geocoding

import com.google.gson.annotations.SerializedName

data class GeocodingModel(

	@field:SerializedName("GeocodingModel")
	val geocodingModel: List<GeocodingModelItem?>? = null
)

data class GeocodingModelItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null,

	@field:SerializedName("local_names")
	val localNames: LocalNames? = null
)

data class LocalNames(

	@field:SerializedName("ar")
	val ar: String? = null,

	@field:SerializedName("ru")
	val ru: String? = null,

	@field:SerializedName("ko")
	val ko: String? = null,

	@field:SerializedName("uk")
	val uk: String? = null,

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("fr")
	val fr: String? = null,

	@field:SerializedName("hi")
	val hi: String? = null,

	@field:SerializedName("pt")
	val pt: String? = null,

	@field:SerializedName("lt")
	val lt: String? = null,

	@field:SerializedName("he")
	val he: String? = null,

	@field:SerializedName("es")
	val es: String? = null,

	@field:SerializedName("zh")
	val zh: String? = null,

	@field:SerializedName("ps")
	val ps: String? = null,

	@field:SerializedName("ht")
	val ht: String? = null,

	@field:SerializedName("hu")
	val hu: String? = null,

	@field:SerializedName("yi")
	val yi: String? = null,

	@field:SerializedName("hy")
	val hy: String? = null,

	@field:SerializedName("yo")
	val yo: String? = null,

	@field:SerializedName("ab")
	val ab: String? = null,

	@field:SerializedName("feature_name")
	val featureName: String? = null,

	@field:SerializedName("af")
	val af: String? = null,

	@field:SerializedName("is")
	val isl: String? = null,

	@field:SerializedName("it")
	val it: String? = null,

	@field:SerializedName("am")
	val am: String? = null,

	@field:SerializedName("an")
	val an: String? = null,

	@field:SerializedName("ja")
	val ja: String? = null,

	@field:SerializedName("rm")
	val rm: String? = null,

	@field:SerializedName("zu")
	val zu: String? = null,

	@field:SerializedName("ro")
	val ro: String? = null,

	@field:SerializedName("ba")
	val ba: String? = null,

	@field:SerializedName("be")
	val be: String? = null,

	@field:SerializedName("bg")
	val bg: String? = null,

	@field:SerializedName("bn")
	val bn: String? = null,

	@field:SerializedName("bo")
	val bo: String? = null,

	@field:SerializedName("sa")
	val sa: String? = null,

	@field:SerializedName("br")
	val br: String? = null,

	@field:SerializedName("sc")
	val sc: String? = null,

	@field:SerializedName("si")
	val si: String? = null,

	@field:SerializedName("ka")
	val ka: String? = null,

	@field:SerializedName("sk")
	val sk: String? = null,

	@field:SerializedName("sl")
	val sl: String? = null,

	@field:SerializedName("ca")
	val ca: String? = null,

	@field:SerializedName("sq")
	val sq: String? = null,

	@field:SerializedName("sr")
	val sr: String? = null,

	@field:SerializedName("kk")
	val kk: String? = null,

	@field:SerializedName("sv")
	val sv: String? = null,

	@field:SerializedName("kn")
	val kn: String? = null,

	@field:SerializedName("ku")
	val ku: String? = null,

	@field:SerializedName("kv")
	val kv: String? = null,

	@field:SerializedName("kw")
	val kw: String? = null,

	@field:SerializedName("co")
	val co: String? = null,

	@field:SerializedName("ta")
	val ta: String? = null,

	@field:SerializedName("ky")
	val ky: String? = null,

	@field:SerializedName("cs")
	val cs: String? = null,

	@field:SerializedName("te")
	val te: String? = null,

	@field:SerializedName("cu")
	val cu: String? = null,

	@field:SerializedName("tg")
	val tg: String? = null,

	@field:SerializedName("cv")
	val cv: String? = null,

	@field:SerializedName("th")
	val th: String? = null,

	@field:SerializedName("cy")
	val cy: String? = null,

	@field:SerializedName("tl")
	val tl: String? = null,

	@field:SerializedName("li")
	val li: String? = null,

	@field:SerializedName("tr")
	val tr: String? = null,

	@field:SerializedName("tt")
	val tt: String? = null,

	@field:SerializedName("de")
	val de: String? = null,

	@field:SerializedName("ln")
	val ln: String? = null,

	@field:SerializedName("lo")
	val lo: String? = null,

	@field:SerializedName("lv")
	val lv: String? = null,

	@field:SerializedName("mi")
	val mi: String? = null,

	@field:SerializedName("ur")
	val ur: String? = null,

	@field:SerializedName("mk")
	val mk: String? = null,

	@field:SerializedName("ml")
	val ml: String? = null,

	@field:SerializedName("mn")
	val mn: String? = null,

	@field:SerializedName("mr")
	val mr: String? = null,

	@field:SerializedName("mt")
	val mt: String? = null,

	@field:SerializedName("el")
	val el: String? = null,

	@field:SerializedName("eo")
	val eo: String? = null,

	@field:SerializedName("my")
	val my: String? = null,

	@field:SerializedName("et")
	val et: String? = null,

	@field:SerializedName("eu")
	val eu: String? = null,

	@field:SerializedName("vi")
	val vi: String? = null,

	@field:SerializedName("ne")
	val ne: String? = null,

	@field:SerializedName("fa")
	val fa: String? = null,

	@field:SerializedName("nl")
	val nl: String? = null,

	@field:SerializedName("no")
	val no: String? = null,

	@field:SerializedName("fi")
	val fi: String? = null,

	@field:SerializedName("fy")
	val fy: String? = null,

	@field:SerializedName("oc")
	val oc: String? = null,

	@field:SerializedName("wo")
	val wo: String? = null,

	@field:SerializedName("ga")
	val ga: String? = null,

	@field:SerializedName("ascii")
	val ascii: String? = null,

	@field:SerializedName("gd")
	val gd: String? = null,

	@field:SerializedName("or")
	val or: String? = null,

	@field:SerializedName("os")
	val os: String? = null,

	@field:SerializedName("gl")
	val gl: String? = null,

	@field:SerializedName("gn")
	val gn: String? = null,

	@field:SerializedName("gu")
	val gu: String? = null,

	@field:SerializedName("gv")
	val gv: String? = null,

	@field:SerializedName("pl")
	val pl: String? = null
)
