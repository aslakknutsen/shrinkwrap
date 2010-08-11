/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.descriptors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.descriptor.api.DescriptorDef;
import org.jboss.shrinkwrap.descriptor.api.DescriptorExporter;

/**
 * Converts a {@link DescriptorDef} to {@link InputStream}.
 * 
 * Asset to bridge ShrinkWrap 'Archives' with ShrinkWrap Descriptors.
 * 
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class DescriptorAsset implements Asset
{
   //-------------------------------------------------------------------------------------||
   // Instance Members -------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /**
    * Underlying descriptor.
    */
   private DescriptorDef<?> descriptorDef;
   
   //-------------------------------------------------------------------------------------||
   // Constructor ------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /**
    * Creates a new {@link Asset} instance backed by the specific {@link DescriptorDef}.
    * 
    * @param descriptorDef The content represented as a DescriptorDef<?>
    * @throws IllegalArgumentException If the descriptorDef were not specified
    */
   public DescriptorAsset(DescriptorDef<?> descriptorDef)
   {
      if(descriptorDef == null)
      {
         throw new IllegalArgumentException("DescriptorDef must be specified");
      }
      this.descriptorDef = descriptorDef;
   }

   //-------------------------------------------------------------------------------------||
   // Required Implementations -----------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /* (non-Javadoc)
    * @see org.jboss.shrinkwrap.api.asset.Asset#openStream()
    */
   public InputStream openStream()
   {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      DescriptorExporter.to(descriptorDef.descriptor(), out);
      return new ByteArrayInputStream(out.toByteArray());
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return DescriptorAsset.class.getSimpleName() + " [content schema=" + descriptorDef.descriptor().getSchemaLocation() + "]";
   }
}
