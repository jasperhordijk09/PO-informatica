from PIL import Image

# ---- SPECIFICEER HIER JE 4 BESTANDEN ----
bestanden = [
    "slime_block.png",
    "ice_block.png",
    "stone_block.png",
    "grass_block.png"
]
# -----------------------------------------

for naam in bestanden:
    img = Image.open(naam)

    # 25% groter
    nieuwe_breedte = int(img.width * 1.25)
    nieuwe_hoogte = int(img.height * 1.25)

    img_groot = img.resize((nieuwe_breedte, nieuwe_hoogte), Image.LANCZOS)

    # Overschrijf het originele bestand
    img_groot.save(naam)

print("Klaar! De vier opgegeven blokken zijn 25% vergroot.")