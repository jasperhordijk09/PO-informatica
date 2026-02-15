import os
from PIL import Image

# Script staat in dezelfde map als de submappen
base_path = "."

# Alleen deze mappen verwerken
allowed_folders = {"fabriek-bg","industrie-bg","pakhuis-bg"}  # <-- pas aan

TARGET_WIDTH = 2048
image_extensions = {".png"}

for folder in os.listdir(base_path):
    folder_path = os.path.join(base_path, folder)

    # Alleen mappen die in allowed_folders staan
    if not os.path.isdir(folder_path) or folder not in allowed_folders:
        continue

    for filename in os.listdir(folder_path):
        name, ext = os.path.splitext(filename)
        ext = ext.lower()

        if ext not in image_extensions:
            continue

        img_path = os.path.join(folder_path, filename)

        with Image.open(img_path) as img:
            width, height = img.size

            # Als de afbeelding smaller is dan 2048, sla over
            if width <= TARGET_WIDTH:
                print(f"Overgeslagen (te smal): {folder}/{filename}")
                continue

            # Bereken crop: middelste 2048 pixels
            left = (width - TARGET_WIDTH) // 2
            right = left + TARGET_WIDTH

            cropped = img.crop((left, 0, right, height))

            # Overschrijf het originele bestand
            cropped.save(img_path)

            print(f"Bijgesneden: {folder}/{filename}")

print("Klaar!")