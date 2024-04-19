package inia;

import org.bukkit.Bukkit;

public enum McVersion {

	UNKNOWN(-1),

	v1_7_R1(1071),
	v1_7_R2(1072),
	v1_7_R3(1073),
	v1_7_R4(1074),

	v1_8_R1(1081),
	v1_8_R2(1082),
	v1_8_R3(1083),

	v1_9_R1(1091),
	v1_9_R2(1092),

	v1_10_R1(1101),

	v1_11_R1(1111),

	v1_12_R1(1121),

	v1_13_R1(1131),
	v1_13_R2(1132),

	v1_14_R1(1141),

	v1_15_R1(1151),

	v1_16_R1(1161),
	v1_16_R2(1162),
	v1_16_R3(1163),

	v1_17_R1(1174, false),

	v1_18_R1(1181, false),
	v1_18_R2(1182, false);

	public static final McVersion CURRENT_VERSION;
	public static final String NM_PACKAGE = "net.minecraft";
	public static final String NMS_PACKAGE_PREFIX = "net.minecraft.server.";
	public static final String OCB_PACKAGE_PREFIX = "org.bukkit.craftbukkit.";

	static {
		String pkgVersion = "";
		
		try {
			String ocbPkg = Bukkit.getServer()
				.getClass()
				.getPackage()
				.getName();
			pkgVersion = ocbPkg.substring(ocbPkg.lastIndexOf('.') + 1);
		} catch (Exception e) {
			Bukkit.getLogger().warning("Cannot detect server version!");
		}

		CURRENT_VERSION = pkgVersion.isEmpty() ?
			UNKNOWN :
			McVersion.valueOf(pkgVersion);
	}

	private final int version;
	private final boolean isNmsPkg;

	McVersion(int version) {
		this.version = version;
		this.isNmsPkg = true;
	}

	McVersion(int version, boolean isNmsPkg) {
		this.version = version;
		this.isNmsPkg = isNmsPkg;
	}

	public static McVersion getCurrent() {
		return CURRENT_VERSION;
	}

	public boolean isNewerThan(McVersion version) {
		return this.version >= version.version;
	}
	
	public boolean isOlderThan(McVersion version) {
		return this.version < version.version;
	}

	public boolean equals(McVersion version) {
		return this.version == version.version;
	}

	public boolean isInRange(McVersion oldVer, McVersion newVer) {
		return this.isNewerThan(oldVer) && this.isOlderThan(newVer) || this.equals(newVer);
	}

	public String getNmsPkg() {
		if(this.isNmsPkg) return NMS_PACKAGE_PREFIX + name();
		return NM_PACKAGE;
	}

	public String getOcbPkg() {
		return OCB_PACKAGE_PREFIX + name();
	}

	public String getPkgVersion() {
		return this.name();
	}

	public int getVersion() {
		return this.version;
	}

	public boolean isNmsPkg() {
		return this.isNmsPkg;
	}
}