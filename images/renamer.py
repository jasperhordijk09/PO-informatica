import os
from PIL import Image

# Script staat in dezelfde map als de submappen
base_path = "."

# Alleen deze mappen verwerken
allowed_folders = {"fabriek-bg", "industrie-bg", "pakhuis-bg"}

image_extensions = {".jpg"}

for folder in os.listdir(base_path):
    folder_path = os.path.join(base_path, folder)

    # Alleen mappen die in allowed_folders staan
    if not os.path.isdir(folder_path) or folder not in allowed_folders:
        continue

    # Prefix = eerste deel van de mapnaam vóór het eerste '-'
    prefix = folder.split("-")[0]

    for filename in os.listdir(folder_path):
        name, ext = os.path.splitext(filename)
        ext = ext.lower()

        if ext not in image_extensions:
            continue

        old_path = os.path.join(folder_path, filename)
        new_name = f"{prefix}-img-{name}.png"
        new_path = os.path.join(folder_path, new_name)

        # Converteren naar PNG
        with Image.open(old_path) as img:
            img.save(new_path, "PNG")

        # Oude bestand verwijderen als het geen PNG was
        if ext != ".png":
            os.remove(old_path)

        print(f"{filename} → {new_name}")

print("Klaar!")
